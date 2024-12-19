package com.empresa.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.empresa.dto.BoletaDTO;
import com.empresa.dto.BoletaDTO1;
import com.empresa.entidad.Boleta;
import com.empresa.entidad.Cliente;
import com.empresa.entidad.Clientedestino;
import com.empresa.entidad.Material;
import com.empresa.entidad.Precio;
import com.empresa.entidad.Ubigeodestino;
import com.empresa.entidad.Ubigeoorigen;
import com.empresa.entidad.Unidadsolicitante;
import com.empresa.servicio.BoletaServicio;
import com.empresa.servicio.ClienteServicio;
import com.empresa.servicio.ClientedestinoServicio;
import com.empresa.servicio.HashServicio;
import com.empresa.servicio.MaterialServicio;
import com.empresa.servicio.PrecioServicio;
import com.empresa.servicio.QRCodeServicio;
import com.empresa.servicio.UbigeodestinoServicio;
import com.empresa.servicio.UbigeoorigenServicio;
import com.empresa.servicio.UnidadsolicitanteServicio;
import com.google.zxing.WriterException;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jfree.util.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;



@RestController
@Slf4j
@RequestMapping("/api/boletas")
public class ApiBoletaController {
    
	 @Autowired
   private HashServicio hashService;
     @Autowired
	private QRCodeServicio qrService;
    @Autowired
    private BoletaServicio boletaServicio;
    @Autowired
    private UbigeoorigenServicio ubigeoorigenServicio;
    @Autowired
    private MaterialServicio materialServicio;
    @Autowired
    private PrecioServicio precioServicio;
    
    @Autowired
    private UbigeodestinoServicio ubigeodestinoServicio;
    @Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ClientedestinoServicio clientedestinoServicio;
	@Autowired
	private UnidadsolicitanteServicio unidadsolicitanteServicio;

    @PutMapping("/{idBoleta}/firmaOrigen")
    public Boleta updateFirmaOrigen(@PathVariable int idBoleta, @RequestParam("file") MultipartFile file) throws IOException {
        Boleta boleta = boletaServicio.buscarPoID(idBoleta).orElseThrow(() -> new RuntimeException("Boleta no encontrada"+idBoleta));

        byte[] firmaOrigen = file.getBytes();
        boleta.setFirmaOrigenEmpresa(firmaOrigen);

        return boletaServicio.guardarActualizarBoleta(boleta);
    }
    
    
    @PutMapping("/actualizaBoleta")
    public ResponseEntity<Boleta> actualiza(@RequestBody Boleta boleta) {
        log.info("METODO --> actualiza " + boleta.getIdBoleta());
        Optional<Boleta> obj = boletaServicio.buscarPoID(boleta.getIdBoleta());
        if (obj.isPresent()) {
            Boleta boletaExistente = obj.get();
            Byte estadoFirma = boletaExistente.getEstadoFirma();
            if (estadoFirma==0) {
                boletaExistente.setFirmaOrigenEmpresa(boleta.getFirmaOrigenEmpresa());
                boletaExistente.setFirmaOrigenEntel(boleta.getFirmaOrigenEntel());
                boletaExistente.setObservacionOrigenEmpresa(boleta.getObservacionOrigenEmpresa());
                boletaExistente.setObservacionOrigenEntel(boleta.getObservacionOrigenEntel());

            } else if (estadoFirma==1) {
                boletaExistente.setFirmaDestinoEmpresa(boleta.getFirmaDestinoEmpresa());
                boletaExistente.setFirmaDestinoEntel(boleta.getFirmaDestinoEntel());
                boletaExistente.setObservacionDestinoEmpresa(boleta.getObservacionDestinoEmpresa());
                boletaExistente.setObservacionDestinoEntel(boleta.getObservacionDestinoEntel());
                boletaExistente.setFechaDestino(new Date());
            }
            boletaExistente.setBotonConsolidacion(boleta.getBotonConsolidacion());
            boletaExistente.setNumguia(boleta.getNumguia());
            boletaExistente.setEstadoFirma(boleta.getEstadoFirma());
            
            Boleta boletaActualizada = boletaServicio.guardarActualizarBoleta(boletaExistente);
            return ResponseEntity.ok(boletaActualizada);
        } else {
            log.error("Id " + boleta.getIdBoleta() + " no existe");
            return ResponseEntity.notFound().build();
        }
    }
//------------------------------genera pdf------------------------------
    @GetMapping("/generaComprobanteDeEntregaPDF/{idBoleta}")
    public ResponseEntity<InputStreamResource> generarPDF(@PathVariable("idBoleta") int idBoleta, HttpServletRequest request) throws IOException, JRException, WriterException {
        List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
        List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);
        List<Ubigeoorigen> ubigeoorigenes = ubigeoorigenServicio.listaParaUbigeoorigen(idBoleta);
        List<Ubigeodestino> ubigeodestinos = ubigeodestinoServicio.listaParaUbigeodestino(idBoleta);

        Boleta boleta = boletas.get(0); // Suponiendo que hay una única boleta

        // Cargar la imagen del logo como recurso desde el classpath
        ClassPathResource resource = new ClassPathResource("static/images/logo1.png");
        if (!resource.exists()) {
            throw new IOException("No se pudo encontrar el archivo logo.png");
        }
        BufferedImage logoImage = ImageIO.read(resource.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(logoImage, "png", baos);
        baos.flush();
        byte[] logoImageBytes = baos.toByteArray();
        baos.close();

        String qrText = construirTextoQR(boleta); // Cambia esto según tus datos
        byte[] qrImageBytes = qrService.generateQRCode(qrText, 100, 100);

        ByteArrayInputStream qrInputStream = new ByteArrayInputStream(qrImageBytes);
        String hash = hashService.generateSHA256(qrText);

        // Convertir las firmas a BufferedImage, usando una imagen en blanco si es null
        BufferedImage blankImage = crearImagenEnBlanco(100, 50); // Ajusta el tamaño según sea necesario

        BufferedImage firmaOrigenEntelImage = boleta.getFirmaOrigenEntel() != null ? 
            ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(boleta.getFirmaOrigenEntel()))) : blankImage;
        BufferedImage firmaOrigenEmpresaImage = boleta.getFirmaOrigenEmpresa() != null ? 
            ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(boleta.getFirmaOrigenEmpresa()))) : blankImage;
        BufferedImage firmaDestinoEntelImage = boleta.getFirmaDestinoEntel() != null ? 
            ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(boleta.getFirmaDestinoEntel()))) : blankImage;
        BufferedImage firmaDestinoEmpresaImage = boleta.getFirmaDestinoEmpresa() != null ? 
            ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(boleta.getFirmaDestinoEmpresa()))) : blankImage;

        // Convertir BufferedImage a InputStream
        InputStream firmaOrigenEntelStream = bufferedImageToInputStream(firmaOrigenEntelImage);
        InputStream firmaOrigenEmpresaStream = bufferedImageToInputStream(firmaOrigenEmpresaImage);
        InputStream firmaDestinoEntelStream = bufferedImageToInputStream(firmaDestinoEntelImage);
        InputStream firmaDestinoEmpresaStream = bufferedImageToInputStream(firmaDestinoEmpresaImage);

        // Llenar parámetros del reporte
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("QrImagen", qrInputStream); // Pasar el InputStream del QR code
        parameters.put("logoEmpresa", new ByteArrayInputStream(logoImageBytes)); // Pasar el InputStream del logo
        parameters.put("Comprobante", new JRBeanCollectionDataSource(materiales));

        parameters.put("hashQR", hash); // Pasar el hash como parámetro
        parameters.put("almacenDepartamentoOrigen", ubigeoorigenes.get(0).getAlmacenDepartamento());
        parameters.put("almacenDepartamentoDestino", ubigeodestinos.get(0).getAlmacenDepartamento());

        parameters.put("numguiaConNg", boletas.get(0).getNumguiaConNg());
        parameters.put("fechaDestino", boletas.get(0).getFechaDestino());
        parameters.put("fechaOrigen", boletas.get(0).getFechaOrigen());
        parameters.put("descripcion", boletas.get(0).getDescripcion());
        parameters.put("observacionOrigenEntel", boletas.get(0).getObservacionOrigenEntel());
        parameters.put("observacionOrigenEmpresa", boletas.get(0).getObservacionOrigenEmpresa());
        parameters.put("observacionDestinoEntel", boletas.get(0).getObservacionDestinoEntel());
        parameters.put("observacionDestinoEmpresa", boletas.get(0).getObservacionDestinoEmpresa());
        parameters.put("firmaOrigenEntel", firmaOrigenEntelStream);
        parameters.put("firmaOrigenEmpresa", firmaOrigenEmpresaStream);
        parameters.put("firmaDestinoEntel", firmaDestinoEntelStream);
        parameters.put("firmaDestinoEmpresa", firmaDestinoEmpresaStream);

        // Llenar el datasource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletas);

        // Cargar y compilar la plantilla del reporte
        String fileDirectory = request.getServletContext().getRealPath("/WEB-INF/reportes/comprovanteEntrega.jasper");
        FileInputStream stream = new FileInputStream(new File(fileDirectory));
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporteComprobanteEntrega.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inStream));
    }

    private BufferedImage crearImagenEnBlanco(int width, int height) {
        BufferedImage blankImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = blankImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return blankImage;
    }

    private InputStream bufferedImageToInputStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    private String construirTextoQR(Boleta boleta) {
        StringBuilder qrTextBuilder = new StringBuilder();
        qrTextBuilder.append("CONZZARTRANS S.R.L ");
        qrTextBuilder.append("\nCMM: ").append(boleta.getCmmConT());
        qrTextBuilder.append(" FLUJO: ").append(boleta.getFlujoConF());
        qrTextBuilder.append(" Nº guia: ").append(boleta.getNumguiaConNg());
        qrTextBuilder.append(" PROFORMA: ").append(boleta.getProformaConPF());
        qrTextBuilder.append("\n Fecha Realizado: ").append(boleta.getFechaDestino());
        qrTextBuilder.append("\n Unidad: ").append(boleta.getUnidadsolicitante().getNombreCompleto());
        qrTextBuilder.append("\n Tipo Transporte: ").append(boleta.getTipoTransporte());
        qrTextBuilder.append("\n Descripcion: ").append(boleta.getDescripcion());
        // Agrega más campos según sea necesario

        String qrText = qrTextBuilder.toString();
        String hash = hashService.generateSHA256(qrText);

        qrTextBuilder.append("\n codigo de seguridad: ").append(hash);

        return qrTextBuilder.toString();
    }

    //----------------------pdf-------------------------------------
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Boleta>> mostrarBoletaDelUsuario(@PathVariable("idUsuario") int idUsuario) {
    	log.info("METODO -->mostrarBoletaDelUsuario");
        return ResponseEntity.ok(boletaServicio.lisatboletasUsuario(idUsuario));
    }    
    @GetMapping("/usuario1/{idUsuario}")
    public ResponseEntity<List<BoletaDTO>> mostrarJsonBoletaPersonalizado(@PathVariable("idUsuario") int idUsuario) {
    	log.info("METODO --> mostrarBoletaDelUsuario");
        List<Boleta> boletas = boletaServicio.lisatboletasUsuario(idUsuario);

        // Convertir lista de Boleta a lista de BoletaDTO
        List<BoletaDTO> dtos = new ArrayList<>();
        for (Boleta boleta : boletas) {
            BoletaDTO dto = BoletaDTO.fromBoleta(boleta);
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    } 
    
    
    
    @GetMapping("/ubigeoorigen/{idBoleta}")
    public ResponseEntity<List<Ubigeoorigen>> mostrarUbigeoorigenDeBoleta(@PathVariable("idBoleta") int idBoleta) {
    	Log.info("METODO -->mostrarBoletaDelUsuario");
        return ResponseEntity.ok(ubigeoorigenServicio.listaUbigeoorigen(idBoleta));
    }  
    
    @GetMapping("/listaDepartamentosDestino")
	public List<String> verDepartamentos() {
	return ubigeodestinoServicio.listaDepartamentos1();
	}
    @GetMapping("/listaProvinciasDestino")
	public List<String> verProvinicias(String departamento) {
		return ubigeodestinoServicio.listaProvincias1(departamento);
	}
    
    @GetMapping("/listaMunicipiosDestino")
	public List<String> verMunicipios(String departamento, String provincia) {
	return ubigeodestinoServicio.listaMunicipios1(departamento, provincia);
	}
    @GetMapping("/listaAlmacenes")
		public List<Ubigeodestino> verAlmacenes(String departamento, String provincia,String municipio) {
		return ubigeodestinoServicio.listaAlmacenes1(departamento, provincia,municipio);
	}
    
    
    
    
    @GetMapping("/listaDepartamentosOrigen")
	public List<String> verDepartamentosorigen() {
		
		return ubigeoorigenServicio.listaDepartamentos();
	}
	
    @GetMapping("/listaProvinciasOrigen")
	public List<String> verProviniciasorigen(String departamento) {
		return ubigeoorigenServicio.listaProvincias(departamento);
	}
	
    @GetMapping("/listaMunicipiosOrigen")
	public List<String> verMunicipiosorigen(String departamento, String provincia) {
		return ubigeoorigenServicio.listaMunicipios(departamento, provincia);
	}
	
    @GetMapping("/listaAlmacenesOrigen")
	public List<Ubigeoorigen> verAlmacenesorigen(String departamento, String provincia,String municipio) {
		return ubigeoorigenServicio.listaAlmacenes(departamento, provincia,municipio);
	}
    
    
    
    
    
    @GetMapping("/cargaCliente")
	public List<Cliente> listaCliente() {
		return clienteServicio.listarTodoLosCliente();
	}
	
    @GetMapping("/cargaClientedestino")
	public List<Clientedestino> listaCliente1() {
		return clientedestinoServicio.listarTodoLosClientedestino();
	}
	
    @GetMapping("/cargaUnidadsolicitante")
	public List<Unidadsolicitante> listaUnidadsolicitante() {
		return unidadsolicitanteServicio.listarTodoLosUnidadsolicitante();
	}
    

@PostMapping("/registrar")
public ResponseEntity<Boleta> registrar(@RequestBody Boleta obj) {
    //int idboleta=obj.getIdBoleta();
	try {
        log.info("METODO --> registrar");
        int ultimaproforma=boletaServicio.ultimoProforma();
        int proforma=ultimaproforma+1;
        obj.setProforma(proforma);
        int ultimoNumguia=boletaServicio.ultimoNumguia();
    	int numguia=ultimoNumguia+1;
    	obj.setNumguia(numguia);
    	
    	
        Boleta savedBoleta = boletaServicio.insertaBoleta(obj);
        //calcularPrecioServicio(idboleta);
        log.info("Boleta guardada: {}", savedBoleta);
        return ResponseEntity.ok(savedBoleta);
    } catch (Exception e) {
        log.error("Error al registrar la boleta", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
   

@PostMapping("/registrarMaterial")
public ResponseEntity<?> registrarmaterial(@RequestBody Material obj) {
	
	try {
        log.info("METODO --> registrar");
        Material savedMaterial = materialServicio.guardarActualizarMaterial(obj);
        log.info("Material guardada: {}", savedMaterial);
        Integer idBoleta =  savedMaterial.getBoleta().getIdBoleta();
        calcularPrecioServicio(idBoleta);
        copiaDescripcionMaterialaBoleta(idBoleta);
        log.info("ID Boleta asociado: {}", idBoleta);
        
        return ResponseEntity.ok(savedMaterial);
    } catch (Exception e) {
        log.error("Error al registrar la material", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

@GetMapping("/consultarBoletaUltimoRegistro1")
public ResponseEntity<Integer> consultarUltimoRegistro() {
    Integer idUltimoRegistro = boletaServicio.obtenerUltimoRegistro1();
    if (idUltimoRegistro != null) {
        return ResponseEntity.ok(idUltimoRegistro);
    } else {
        return ResponseEntity.notFound().build();
    }
}


@GetMapping("/consultarBoletaUltimoRegistro")
public ResponseEntity<List<Integer>> listaUltimoBoleta() {
	log.info("METODO --> listaUltimoBoleta");
	return ResponseEntity.ok(boletaServicio.listaUltimoRegistro());
}


@GetMapping("/listaBoleta1")
public ResponseEntity<List<BoletaDTO1>> listaBoleta1() {
    log.info("METODO --> listaBoleta1");

    // Obtener las últimas 15 boletas
    List<Boleta> boletas = boletaServicio.obtener15UltimasBoletas();

    // Mapear las boletas a BoletaDTO1 directamente
    List<BoletaDTO1> boletaDTO1s = boletas.stream()
        .map(boleta -> {
            BoletaDTO1 dto = new BoletaDTO1();
            dto.setIdBoleta(boleta.getIdBoleta());
            dto.setCmm(boleta.getCmm());
            dto.setFlujo(boleta.getFlujo());
            return dto;
        })
        .collect(Collectors.toList());

    return ResponseEntity.ok(boletaDTO1s);
}

@GetMapping("/verificarCmm")
public ResponseEntity<Boolean> verificarCmm(@RequestParam String cmm) {
    boolean existe = boletaServicio.existeCmm(cmm);
    return ResponseEntity.ok(existe);
}







@GetMapping("/listaBoletasFirmadasOrigen")
public ResponseEntity<List<BoletaDTO1>> listaBoletaFirmadasOrigen() {
    log.info("METODO --> listaBoleta1");
    List<Boleta> boletas = boletaServicio.listaboletasFirmadasOrigen();
    List<BoletaDTO1> boletaDTO1s = boletas.stream()
        .map(boleta -> {
            BoletaDTO1 dto = new BoletaDTO1();
            dto.setIdBoleta(boleta.getIdBoleta());
            dto.setCmm(boleta.getCmm());
            //dto.setFlujo(boleta.getFlujo());
            dto.setDepartamentoUbigeoDestino(boleta.getUbigeodestino().getDepartamento());
            dto.setDepartamentoUbigeoOrigen(boleta.getUbigeoorigen().getDepartamento());
            return dto;
        })
        .collect(Collectors.toList());
    return ResponseEntity.ok(boletaDTO1s);
}


@PutMapping("/actualizaBoletaSoloCampoIdUsuario2")
public ResponseEntity<Boleta> actualizaIdUsuario2(@RequestBody Boleta boleta) {
    log.info("METODO --> actualiza " + boleta.getIdBoleta());
    Optional<Boleta> obj = boletaServicio.buscarPoID(boleta.getIdBoleta());
    if (obj.isPresent()) {
        Boleta boletaExistente = obj.get();
       // Byte estadoFirma = boletaExistente.getEstadoFirma();
        boletaExistente.setUsuario2(boleta.getUsuario2());
        Boleta boletaActualizada = boletaServicio.guardarActualizarBoleta(boletaExistente);
        return ResponseEntity.ok(boletaActualizada);
    } else {
        log.error("Id " + boleta.getIdBoleta() + " no existe");
        return ResponseEntity.notFound().build();
    }
}




@GetMapping("/listaUsuarioAsignadoCmm")
public ResponseEntity<List<BoletaDTO1>> listaUsuarioConRelacionCmm() {
    log.info("METODO --> listaBoleta1");
    List<Boleta> boletas = boletaServicio.listaboletasRelacionadasConUsuario();
    List<BoletaDTO1> boletaDTO1s = boletas.stream()
        .map(boleta -> {
            BoletaDTO1 dto = new BoletaDTO1();
            dto.setIdBoleta(boleta.getIdBoleta());
            dto.setCmm(boleta.getCmm());
            //dto.setFlujo(boleta.getFlujo());
          //  dto.setNombreCompletoUsuario(boleta.getUsuario().getNombreCompleto());
            dto.setNombreCompletoUsuario(boleta.getUsuario2().getNombreCompleto());
            dto.setDepartamentoUbigeoDestino(boleta.getUbigeodestino().getDepartamento());
            dto.setDepartamentoUbigeoOrigen(boleta.getUbigeoorigen().getDepartamento());
            return dto;
        })
        .collect(Collectors.toList());
    return ResponseEntity.ok(boletaDTO1s);
}
//----------------------asiganacion en origen---------------------
@PutMapping("/actualizaBoletaSoloCampoIdUsuario")
public ResponseEntity<Boleta> actualizaIdUsuario(@RequestBody Boleta boleta) {
    log.info("METODO --> actualiza " + boleta.getIdBoleta());
    Optional<Boleta> obj = boletaServicio.buscarPoID(boleta.getIdBoleta());
    if (obj.isPresent()) {
        Boleta boletaExistente = obj.get();
       // Byte estadoFirma = boletaExistente.getEstadoFirma();
        boletaExistente.setUsuario(boleta.getUsuario());
        Boleta boletaActualizada = boletaServicio.guardarActualizarBoleta(boletaExistente);
        return ResponseEntity.ok(boletaActualizada);
    } else {
        log.error("Id " + boleta.getIdBoleta() + " no existe");
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/listaBoletasSinAsignarOrigen")
public ResponseEntity<List<BoletaDTO1>> listaBoletasinAsignarOrigen() {
    log.info("METODO --> listaBoleta1");
    List<Boleta> boletas = boletaServicio.listaBolestasSinAsignarEnOrigen();
    List<BoletaDTO1> boletaDTO1s = boletas.stream()
        .map(boleta -> {
            BoletaDTO1 dto = new BoletaDTO1();
            dto.setIdBoleta(boleta.getIdBoleta());
            dto.setCmm(boleta.getCmm());
            //dto.setFlujo(boleta.getFlujo());
            dto.setDepartamentoUbigeoDestino(boleta.getUbigeodestino().getDepartamento());
            dto.setDepartamentoUbigeoOrigen(boleta.getUbigeoorigen().getDepartamento());
            return dto;
        })
        .collect(Collectors.toList());
    return ResponseEntity.ok(boletaDTO1s);
}


@GetMapping("/listaUsuarioAsignadosOrigen")
public ResponseEntity<List<BoletaDTO1>> listaUsuariosAsignadosOrigen() {
    log.info("METODO --> listaBoleta1");
    List<Boleta> boletas = boletaServicio.listaboletasAsiganadosOrigen();
    List<BoletaDTO1> boletaDTO1s = boletas.stream()
        .map(boleta -> {
            BoletaDTO1 dto = new BoletaDTO1();
            dto.setIdBoleta(boleta.getIdBoleta());
            dto.setCmm(boleta.getCmm());
            //dto.setFlujo(boleta.getFlujo());
          //  dto.setNombreCompletoUsuario(boleta.getUsuario().getNombreCompleto());
            dto.setNombreCompletoUsuario(boleta.getUsuario().getNombreCompleto());
            dto.setDepartamentoUbigeoDestino(boleta.getUbigeodestino().getDepartamento());
            dto.setDepartamentoUbigeoOrigen(boleta.getUbigeoorigen().getDepartamento());
            return dto;
        })
        .collect(Collectors.toList());
    return ResponseEntity.ok(boletaDTO1s);
}


public BigDecimal calcularPrecioServicio(int idBoleta) {
    List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
    List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);
    List<Precio> precios = precioServicio.listaParaPrecio(idBoleta);

    // Verificar que las listas no estén vacías
    if (boletas.isEmpty() || materiales.isEmpty() || precios.isEmpty()) {
        throw new RuntimeException("Datos no encontrados para idBoleta: " + idBoleta);
    }

    BigDecimal precioFijado = BigDecimal.ZERO;

    BigDecimal volumen = materiales.get(0).getVolumen();
    Byte cobrarPor = materiales.get(0).getCobrarpor();
    Byte prioridadTransporte = boletas.get(0).getPrioridadtransporte();

    BigDecimal normalMinima = precios.get(0).getNormalMinima();
    BigDecimal normalM3 = precios.get(0).getNormalM3();
    BigDecimal normalKg = precios.get(0).getNormalKgAdicional();

    BigDecimal expresMinima = precios.get(0).getExpresMinimo();
    BigDecimal expresM3 = precios.get(0).getExpresM3();
    BigDecimal expresKg = precios.get(0).getExpresKgAdicional();

    if (prioridadTransporte == 0) { // Transporte por carretera
        if (cobrarPor == 0) { // Cobrar por kg
            BigDecimal pesoTotal = materiales.stream()
                .map(Material::getPeso)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal calculo = pesoTotal.subtract(new BigDecimal("500"));
            if (pesoTotal.compareTo(new BigDecimal("500")) <= 0) {
                precioFijado = normalMinima;
            } else {
                precioFijado = normalMinima.add(calculo.multiply(normalKg));
            }
        } else if (cobrarPor == 1) { // Cobrar por m3
            precioFijado = volumen.multiply(normalM3);
        }
    } else if (prioridadTransporte == 1) { // Transporte por avión
        if (cobrarPor == 0) { // Cobrar por kg
            BigDecimal pesoTotal = materiales.stream()
                .map(Material::getPeso)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal calculo = pesoTotal.subtract(new BigDecimal("500"));
            if (pesoTotal.compareTo(new BigDecimal("500")) <= 0) {
                precioFijado = expresMinima;
            } else {
                precioFijado = expresMinima.add(calculo.multiply(expresKg));
            }
        } else if (cobrarPor == 1) { // Cobrar por m3
            precioFijado = volumen.multiply(expresM3);
        }
    }

    Boleta primeraBoleta = boletas.get(0);
    primeraBoleta.setPrecioServicio(precioFijado);
    boletaServicio.guardarActualizarBoleta(primeraBoleta);

    return precioFijado;
}



public List<Boleta>copiaDescripcionMaterialaBoleta( int idBoleta) {
    // Obtener las boletas y materiales asociados
    List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
    List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);

    // Manejar el caso donde no se encuentran datos
    if (boletas.isEmpty() || materiales.isEmpty()) {
        throw new RuntimeException("Datos no encontrados para idBoleta: " + idBoleta);
    }
    try {
    	 Boleta boleta = boletas.get(0);
 	    String acumuladorEnBoleta = boleta.getAcumulador();
 	    if (acumuladorEnBoleta == null) {
 	        acumuladorEnBoleta = "";
 	    }
 	     StringBuilder solodescripcionmaterial = new StringBuilder();
 	    for (Material material : materiales) {
 	        String descripcionmaterial1 = material.getDescripcion();
 	       solodescripcionmaterial.append(descripcionmaterial1).append(". ");
 	    }
 	   if (solodescripcionmaterial.length() > 0) {
 		  solodescripcionmaterial.setLength(solodescripcionmaterial.length() - 1); // Eliminar el último espacio
 	    }
 	    String resultado1 = solodescripcionmaterial.toString();

 	    // Construir la descripción concatenada de los materiales
 	    StringBuilder tododescripcionmaterial = new StringBuilder();
 	    	  
 	    for (Material material : materiales) {
 	    	 String descripcionmaterial = material.getDescripcion();
             BigDecimal kilogramo = material.getPeso();
             BigDecimal cantidad = material.getCantidad();
             BigDecimal volumen = material.getVolumen();
             byte cobradopor = material.getCobrarpor();
             String valordecobrarpor = (cobradopor == 0) ? "Cobrado:Kg" : "Cobrado:M3";
             String observaciones = material.getObservaciones();
             tododescripcionmaterial.append(descripcionmaterial)
             .append(", ")
             .append("Peso: ").append(kilogramo)
             .append(", ")
             .append("Cantidad: ").append(cantidad)
             .append(", ")
             .append("Volumen: ").append(volumen)
             .append(", ")
             .append(valordecobrarpor)
             .append(", ")
             .append("Observaciones: ").append(observaciones)
             .append(".\n");
 	    }

 	    // Eliminar el último espacio y punto si es necesario
 	    if (tododescripcionmaterial.length() > 0) {
 	        tododescripcionmaterial.setLength(tododescripcionmaterial.length() - 1); // Eliminar el último espacio
 	    }
 	    
 	    // Convertir a String y mostrar el resultado
 	    String resultado = tododescripcionmaterial.toString();

 	    // Actualizar la boleta con la nueva descripción y acumulador
 	    boleta.setAcumulador(acumuladorEnBoleta + (acumuladorEnBoleta.isEmpty() ? "" : "\n*") + resultado);
 	    boleta.setDescripcion(resultado1);

 	    // Guardar la boleta actualizada en la base de datos
 	    boletaServicio.guardarActualizarBoleta(boleta);

 	    // Retornar las boletas
 	    return boletas;
    } catch (Exception e) {
        // Manejo de excepción
        e.printStackTrace(); // Opcional: imprimir la excepción para debug
        throw new RuntimeException("Error al copiar materiales a boleta: " + e.getMessage());
    }
    // Obtener la primera boleta de la lista
   
}

@GetMapping("/verificarIdUbigeoorigenIdUbigeodestino/{valorUbigeoorigen}-{valorUbigeodestino}")
public ResponseEntity<Boolean> verificarUbigeos(
    @PathVariable("valorUbigeoorigen") int valorUbigeoorigen,
    @PathVariable("valorUbigeodestino") int valorUbigeodestino) {
    log.info("METODO --> verificarUbigeos");
    boolean existe = precioServicio.existeIdUbigeoorigenIdUbigeodestino(valorUbigeoorigen, valorUbigeodestino);
   // Devolvemos true si existe, false si no existe
    return ResponseEntity.ok(existe);
}


@PostMapping("/resgistraActualizaPrecio")
public ResponseEntity<Precio> registrarPrecio(@RequestBody Precio obj) {
	log.info("METODO --> registrar");
	//obj.setFechaRegistro(new Date());
	return ResponseEntity.ok(precioServicio.insertaActualizaPrecio(obj));
}


@GetMapping("/listaParaTablaGeneral/{idBoleta}")
public ResponseEntity<Boleta> listatodoPorId(@PathVariable int idBoleta) {
	log.info("METODO --> buscarPorId " + idBoleta);
	List<Boleta> obj = boletaServicio.listaParaTablaPorID(idBoleta);
	if (!obj.isEmpty()) {
		return ResponseEntity.ok(obj.get(0));
	} else {
		log.error("Id " + idBoleta + " no existe");
		return ResponseEntity.notFound().build();
	}
}

@GetMapping("/consultaPorCmmRangoFecha1")
public ResponseEntity<List<Boleta>> listaBoletasenConsolidacionPrincipal(
        @RequestParam(required = false) String cmm,
        @RequestParam(required = false, name = "fecha_desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
        @RequestParam(required = false, name = "fecha_hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) {
    
    log.info("METODO --> listaBoletasenConsolidacionPrincipal");

    // Convertir los parámetros "null" a null reales
    if ("null".equals(cmm)) {
        cmm = null;
    }
    if ("null".equals(fechaDesde)) {
        fechaDesde = null;
    }
    if ("null".equals(fechaHasta)) {
        fechaHasta = null;
    }

    // Verificar si se proporcionaron ambos parámetros de fecha
    if (fechaDesde != null && fechaHasta != null) {
        return ResponseEntity.ok(boletaServicio.listaDentrodeUnRangoFecha(fechaDesde, fechaHasta));
    }

    // Si no se proporcionan las fechas, verificar si el parámetro cmm está presente
    if (cmm != null && !cmm.isEmpty()) {
        return ResponseEntity.ok(boletaServicio.buscarPorCmm(cmm));
    }

    // Si no se proporcionaron ni fechas ni cmm, devolver la lista principal
    return ResponseEntity.ok(boletaServicio.listaParaTablaPrincipal());
}

@GetMapping("/consultaPorCmmRangoFecha2")
public ResponseEntity<List<Boleta>> listaBoletasenConsolidacionPrincipal2(
        @RequestParam(required = false) String cmm,
        @RequestParam(required = false, name = "fecha_desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
        @RequestParam(required = false, name = "fecha_hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) {
    
    log.info("METODO --> listaBoletasenConsolidacionPrincipal");

    // Convertir los parámetros "null" a null reales
    if ("null".equals(cmm)) {
        cmm = null;
    }
    if ("null".equals(fechaDesde)) {
        fechaDesde = null;
    }
    if ("null".equals(fechaHasta)) {
        fechaHasta = null;
    }

    // Verificar si se proporcionaron ambos parámetros de fecha
    if (fechaDesde != null && fechaHasta != null) {
        return ResponseEntity.ok(boletaServicio.listaDentrodeUnRangoFecha(fechaDesde, fechaHasta));
    }

    // Si no se proporcionan las fechas, verificar si el parámetro cmm está presente
    if (cmm != null && !cmm.isEmpty()) {
        return ResponseEntity.ok(boletaServicio.buscarPorCmm(cmm));
    }

    // Si no se proporcionaron ni fechas ni cmm, devolver la lista principal
    return ResponseEntity.ok(boletaServicio.listaParaTablaPrincipal2());
}

@GetMapping("buscarAcumulador/{idBoleta}")
public ResponseEntity<Boleta> buscarPorIdparaAcumulador(@PathVariable int idBoleta) {
	log.info("METODO --> buscarPorId " + idBoleta);
	Optional<Boleta> obj = boletaServicio.buscarPoID(idBoleta);
	if (obj.isPresent()) {
		return ResponseEntity.ok(obj.get());
	} else {
		log.error("Id " + idBoleta + " no existe");
		return ResponseEntity.notFound().build();
	}
}



@GetMapping("copiaMaterialesAboleta1/{idBoleta}")
public ResponseEntity<Boleta> copiaMaterialesABoletas(@PathVariable int idBoleta) {
    log.info("Método --> buscarPorId: " + idBoleta);
      // Esto obtendrá el nombre del usuario autenticado

    // Obtener la boleta y los materiales asociados
    Optional<Boleta> optionalBoleta = boletaServicio.buscarPoID(idBoleta);
    List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);

    // Manejar el caso donde no se encuentran datos
    if (!optionalBoleta.isPresent() || materiales.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 si no se encuentran datos
    }

    try {
        Boleta boleta = optionalBoleta.get();
        String acumuladorEnBoleta = Optional.ofNullable(boleta.getAcumulador()).orElse("");

        StringBuilder cambiosDescripcionMaterial = new StringBuilder();

        for (Material material : materiales) {
            agregarCambiosSiNecesario(cambiosDescripcionMaterial, acumuladorEnBoleta, material);
        }

        if (cambiosDescripcionMaterial.length() > 0) {
            String resultadoCambios = cambiosDescripcionMaterial.toString();
            boleta.setAcumulador(acumuladorEnBoleta + (acumuladorEnBoleta.isEmpty() ? "" : "\n*") + resultadoCambios);
            boleta.setDescripcion(generarDescripcionMaterial(materiales));

            // Guardar la boleta actualizada en la base de datos
            boletaServicio.guardarActualizarBoleta(boleta);
        } else {
            log.info("No se encontraron cambios en los materiales.");
        }

        return ResponseEntity.ok(boleta); // Retorna la boleta actualizada

    } catch (Exception e) {
        log.error("Error al copiar materiales a boleta: " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Retorna 500 en caso de error
    }
}

private void agregarCambiosSiNecesario(StringBuilder cambios, String acumulador, Material material) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String fechaHoraActual = LocalDateTime.now().format(formatter);
	
	String descripcionActual = material.getDescripcion();
    String kilogramoActual = material.getPesoKg();
    BigDecimal cantidadActual = material.getCantidad();
    String volumenActual = material.getVolumenM3();
    byte cobradoporActual = material.getCobrarpor();
    String valordecobrarporActual = (cobradoporActual == 0) ? "Cobrado:Kg" : "Cobrado:M3";
    String observacionesActual = material.getObservaciones();

    if (!acumulador.contains(descripcionActual)) {
        cambios.append("Descripción modificada: ").append(descripcionActual)
               .append(" (Modificado el ").append(fechaHoraActual).append(")\n");
    }
    if (!acumulador.contains(kilogramoActual)) {
        cambios.append("Peso modificado: ").append(kilogramoActual)
               .append(" (Modificado el ").append(fechaHoraActual).append(")\n");
    }
    if (!acumulador.contains(cantidadActual.toString())) {
        cambios.append("Cantidad modificada: ").append(cantidadActual)
               .append(" (Modificado el ").append(fechaHoraActual).append(")\n");
    }
    if (!acumulador.contains(volumenActual)) {
        cambios.append("Volumen modificado: ").append(volumenActual)
               .append(" (Modificado el ").append(fechaHoraActual).append(")\n");
    }
    if (!acumulador.contains(valordecobrarporActual)) {
        cambios.append("Valor a Cobrar modificado: ").append(valordecobrarporActual)
               .append(" (Modificado el ").append(fechaHoraActual).append(")\n");
    }
    if (!acumulador.contains(observacionesActual)) {
        cambios.append("Observaciones modificadas: ").append(observacionesActual)
               .append(" (Modificado el ").append(fechaHoraActual).append(")\n");
    }
}

private String generarDescripcionMaterial(List<Material> materiales) {
    return materiales.stream()
                     .map(Material::getDescripcion)
                     .collect(Collectors.joining(". "));
}







}
