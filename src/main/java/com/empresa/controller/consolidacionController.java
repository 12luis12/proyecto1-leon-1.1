package com.empresa.controller;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;

import java.io.OutputStream;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Boleta;
import com.empresa.entidad.Material;
import com.empresa.entidad.Precio;
import com.empresa.servicio.BoletaServicio;
import com.empresa.servicio.MaterialServicio;
import com.empresa.servicio.PrecioServicio;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@Controller

public class consolidacionController {
	@Autowired
	private BoletaServicio boletaServicio;
	@Autowired
	private PrecioServicio precioServicio;
	@Autowired
	private MaterialServicio materialServicio;

	private static final BigDecimal CINCO_CIENTOS = new BigDecimal("500");

	@GetMapping("/calcularPrecioServicio/{idBoleta}")
	public ResponseEntity<Boleta> calcularprecioServicio(@PathVariable("idBoleta") int idBoleta) {
	    List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
	    List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);
	    List<Precio> precios = precioServicio.listaParaPrecio(idBoleta);

	    // Verificar que las listas no estén vacías
	    if (boletas.isEmpty() || materiales.isEmpty() || precios.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(null);
	    }

	    BigDecimal preciofijado = BigDecimal.ZERO;
	    BigDecimal volumen = materiales.get(0).getVolumen();
	    Byte cobrarpor = materiales.get(0).getCobrarpor();
	    Byte prioridadtransporte = boletas.get(0).getPrioridadtransporte();

	    BigDecimal normalminima = precios.get(0).getNormalMinima();
	    BigDecimal normalm3 = precios.get(0).getNormalM3();
	    BigDecimal normalkg = precios.get(0).getNormalKgAdicional();

	    BigDecimal expresminima = precios.get(0).getExpresMinimo();
	    BigDecimal expresm3 = precios.get(0).getExpresM3();
	    BigDecimal expreskg = precios.get(0).getExpresKgAdicional();

	    BigDecimal CINCO_CIENTOS = new BigDecimal("500");

	    // Lógica de cálculo
	    if (prioridadtransporte == 0) { // transporte por carretera
	        if (cobrarpor == 0) { // cobrar por kg
	            BigDecimal preciofinal = BigDecimal.ZERO;
	            for (Material material : materiales) {
	                BigDecimal peso = material.getPeso();
	                preciofinal = preciofinal.add(peso);
	            }
	            BigDecimal calculo = preciofinal.subtract(CINCO_CIENTOS);
	            if (preciofinal.compareTo(CINCO_CIENTOS) <= 0) {
	                preciofijado = normalminima;
	            } else {
	                preciofijado = normalminima.add(calculo.multiply(normalkg));
	            }
	        } else if (cobrarpor == 1) { // cobrar por m3
	            preciofijado = volumen.multiply(normalm3);
	        }
	    } else if (prioridadtransporte == 1) { // transporte por avión
	        if (cobrarpor == 0) { // cobrar por kg
	            BigDecimal preciofinal = BigDecimal.ZERO;
	            for (Material material : materiales) {
	                BigDecimal peso = material.getPeso();
	                preciofinal = preciofinal.add(peso);
	            }
	            BigDecimal calculo = preciofinal.subtract(CINCO_CIENTOS);
	            if (preciofinal.compareTo(CINCO_CIENTOS) <= 0) {
	                preciofijado = expresminima;
	            } else {
	                preciofijado = expresminima.add(calculo.multiply(expreskg));
	            }
	        } else if (cobrarpor == 1) { // cobrar por m3
	            preciofijado = volumen.multiply(expresm3);
	        }
	    }

	    // Actualizar y guardar la boleta
	    Boleta primeraBoleta = boletas.get(0);
	    primeraBoleta.setPrecioServicio(preciofijado);
	    boletaServicio.guardarActualizarBoleta(primeraBoleta);

	    return ResponseEntity.ok(primeraBoleta);
	}


	@RequestMapping("/consultaPorCmm")
	
	public String listartablaprincipal(HttpSession session) {
		List<Boleta> data = boletaServicio.listaParaTablaPrincipal();
		session.setAttribute("todos", data);
		return "verConsolidaciones";
	}
    
	@RequestMapping("/consultaPorCmm1")
	@ResponseBody
	public List<Boleta> consulta1() {
		 return boletaServicio.listaParaTablaPrincipal();      
			
	}
     
	@RequestMapping("/muestraenlatablaunSoloCmm")
	@ResponseBody
	public List<Boleta> consultabusacrporCmm(String cmm) {
		 return boletaServicio.buscarPorCmm(cmm);
			
	}
	
	@RequestMapping("/consultaPorCmmRangoFecha")
	@ResponseBody
	public List<Boleta> listartablaprincipalporangofecha(
			@RequestParam(required = false) String cmm,
			@RequestParam(required=false,name="fecha_desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
			@RequestParam(required=false,name="fecha_hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) {
		  if (fechaDesde != null && fechaHasta != null) {
			  return boletaServicio.listaDentrodeUnRangoFecha(fechaDesde, fechaHasta);
		  }
		  else if (cmm != null) {
	            return boletaServicio.buscarPorCmm(cmm);
	        }
		  else {
			  return boletaServicio.listaParaTablaPrincipal();
	        }
		  
	}
	//@RequestMapping(value = "/consultaBoletaPdf", method = RequestMethod.GET)
	@RequestMapping("/consultaBoletaPdf")
	@ResponseBody
	public void generarReporte(HttpServletRequest request, HttpServletResponse response,
	                           @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
	                           @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) {

	    try {
	        // Obtén la lista de boletas entre las fechas especificadas
	        List<Boleta> boletas = boletaServicio.listaDentrodeUnRangoFecha(fechaDesde, fechaHasta);
	        //Log.info("METODO -->loque lleva boletas-----------"+boletas.get(0).getUnidadSolicitante().getNombreCompleto());
	        // Crea el data source
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletas);
	        
	        
	        // Configura los parámetros
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("ds", dataSource);
	       // parameters.put("datsetunidadsolicitante", boletas.get(0).getUnidadSolicitante());

	        // Carga el archivo .jasper
	        //InputStream jasperStream = this.getClass().getResourceAsStream("/WEB-INF/reportes/proyectoBoleta.jasper");
	        String fileDitectory = request.getServletContext().getRealPath("/WEB-INF/reportes/proyectoBoleta.jasper");
			FileInputStream stream = new FileInputStream(new File(fileDitectory));
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);

	        // Llena el reporte con los datos
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

	        // Configura la respuesta HTTP para enviar el reporte en formato PDF
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=reporteTransporte.pdf");

	        OutputStream outStream = response.getOutputStream();
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	        
	        outStream.flush();
	        outStream.close();
	        stream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Manejar cualquier excepción apropiadamente aquí
	    }
	}



	
	@RequestMapping("/consultaListaGeneral")
	@ResponseBody()
	public List<Boleta> pasassa(int filtro) {
		List<Boleta> resul = boletaServicio.listaParaTablaPorID(filtro);
		return resul;
	}

	@PutMapping("/guardaConsolidacion")
	public ResponseEntity<Boleta> actualizaCampoConsolidacion(@RequestParam int id) {
		Optional<Boleta> boletaOptional = boletaServicio.buscarPoID(id);

		if (boletaOptional.isPresent()) {
			Boleta boleta = boletaOptional.get();
			boleta.setBotonConsolidacion((byte) 2); // Asigna directamente el valor
			boletaServicio.insertaBoleta(boleta);
			return ResponseEntity.ok(boleta); // Devuelve la boleta actualizada como JSON
		} else {
			return ResponseEntity.notFound().build(); // Maneja el caso en el que no se encuentra la boleta
		}
	}
	
	
	@PutMapping("/guardaConsolidacion2a3")
	public ResponseEntity<Boleta> actualizaCampoConsolidacion2a3(@RequestParam int id) {
		Optional<Boleta> boletaOptional = boletaServicio.buscarPoID(id);

		if (boletaOptional.isPresent()) {
			Boleta boleta = boletaOptional.get();
			boleta.setBotonConsolidacion((byte) 3); // Asigna directamente el valor
			boletaServicio.insertaBoleta(boleta);
			return ResponseEntity.ok(boleta); // Devuelve la boleta actualizada como JSON
		} else {
			return ResponseEntity.notFound().build(); // Maneja el caso en el que no se encuentra la boleta
		}
	}
	
	
	
	@PutMapping("/guardaConsolidacion2a4")
	public ResponseEntity<Boleta> actualizaCampoConsolidacion2a4(@RequestParam int id) {
		Optional<Boleta> boletaOptional = boletaServicio.buscarPoID(id);

		if (boletaOptional.isPresent()) {
			Boleta boleta = boletaOptional.get();
			boleta.setBotonConsolidacion((byte) 4); // Asigna directamente el valor
			boletaServicio.insertaBoleta(boleta);
			return ResponseEntity.ok(boleta); // Devuelve la boleta actualizada como JSON
		} else {
			return ResponseEntity.notFound().build(); // Maneja el caso en el que no se encuentra la boleta
		}
	}
	// -------------------------------------------------

	@RequestMapping("/consultaPrecio")
	@ResponseBody()
	public List<Precio> listarprecio(int filtro) {
		List<Precio> resul = precioServicio.listaParaPrecio(filtro);
		return resul;
	}

}
