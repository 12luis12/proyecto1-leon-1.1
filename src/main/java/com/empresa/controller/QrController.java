package com.empresa.controller;

import com.empresa.entidad.Boleta;
import com.empresa.entidad.Material;

import com.empresa.entidad.Precio;
import com.empresa.entidad.Ubigeodestino;
import com.empresa.entidad.Ubigeoorigen;
import com.empresa.entidad.Unidadsolicitante;
import com.empresa.servicio.BoletaServicio;
import com.empresa.servicio.ConversionLetrasServicio;
import com.empresa.servicio.HashServicio;
import com.empresa.servicio.MaterialServicio;
import com.empresa.servicio.PrecioServicio;
import com.empresa.servicio.QRCodeServicio;
import com.empresa.servicio.UbigeodestinoServicio;
import com.empresa.servicio.UbigeoorigenServicio;
import com.empresa.servicio.UnidadsolicitanteServicio;

import com.google.zxing.WriterException;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QrController {

    @Autowired
    private HashServicio hashService;

    @Autowired
    private QRCodeServicio qrService;

    @Autowired
    private MaterialServicio materialServicio;

    @Autowired
    private BoletaServicio boletaServicio;
    @Autowired
    private UnidadsolicitanteServicio unidadsolicitanteServicio;
    @Autowired
    private ConversionLetrasServicio conversionLetrasServicio;
    @Autowired
    private PrecioServicio precioServicio;
    @Autowired
    private UbigeoorigenServicio ubigeoorigenServicio;
    @Autowired
    private UbigeodestinoServicio ubigeodestinoServicio;

       
    @RequestMapping("/generaYPFB")
    @ResponseBody
    public void generaYPFB(HttpServletRequest request, HttpServletResponse response, @RequestParam int idBoleta) throws IOException, JRException, WriterException {
        	// Obtener datos de la boleta
        List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
        List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);
        List<Unidadsolicitante> UnidaSolicitantes = unidadsolicitanteServicio.listaParaUnidaSolicitante(idBoleta);
        
        List<Precio> precios = precioServicio.listaParaPrecio(idBoleta);
        
        
        Boleta boleta = boletas.get(0); // Suponiendo que hay una única boleta
     
        // Cargar la imagen del logo como recurso desde el classpath
        ClassPathResource resource = new ClassPathResource("static/images/imagenypfd.png");
        if (!resource.exists()) {
            throw new IOException("No se pudo encontrar el archivo logo.png");
        }

        BufferedImage logoImage = ImageIO.read(resource.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(logoImage, "png", baos);
        baos.flush();
        byte[] logoImageBytes = baos.toByteArray();
        baos.close();

        // Generar QR code
        String qrText = construirTextoQR(boleta); // Cambia esto según tus datos
        byte[] qrImageBytes = qrService.generateQRCode(qrText, 110, 110);

        ByteArrayInputStream qrInputStream = new ByteArrayInputStream(qrImageBytes);
        String hash = hashService.generateSHA256(qrText);
        // Llenar parámetros del reporte
        
        String precioLetras =  conversionLetrasServicio.NumeroALetras(boleta.getPrecioServicio());

       // System.out.println("cmmConT: " + boleta.getCmmConT());
       // System.out.println("precionConBs: " + boleta.getPrecionConBs());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logoEmpresa", new ByteArrayInputStream(logoImageBytes)); // Pasar el InputStream del logo
       
        // Llenar el datasource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletas);

        // Cargar y compilar la plantilla del reporte
        String fileDirectory = request.getServletContext().getRealPath("/WEB-INF/reportes/YPFB.jasper");
        FileInputStream stream = new FileInputStream(new File(fileDirectory));
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporteypfb.pdf");

        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        outStream.flush();
        outStream.close();
        stream.close();
    }

    
    @RequestMapping("/generateProformaPDF")
    @ResponseBody
    public void generateReport(HttpServletRequest request, HttpServletResponse response, @RequestParam int idBoleta) throws IOException, JRException, WriterException {
        // Obtener datos de la boleta
        List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
        List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);
        List<Unidadsolicitante> Unidadsolicitantes = unidadsolicitanteServicio.listaParaUnidaSolicitante(idBoleta);
        
        List<Precio> precios = precioServicio.listaParaPrecio(idBoleta);
        
        
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

        // Generar QR code
        String qrText = construirTextoQR(boleta); // Cambia esto según tus datos
        byte[] qrImageBytes = qrService.generateQRCode(qrText, 110, 110);

        ByteArrayInputStream qrInputStream = new ByteArrayInputStream(qrImageBytes);
        String hash = hashService.generateSHA256(qrText);
        // Llenar parámetros del reporte
        
        String precioLetras =  conversionLetrasServicio.NumeroALetras(boleta.getPrecioServicio());

       // System.out.println("cmmConT: " + boleta.getCmmConT());
       // System.out.println("precionConBs: " + boleta.getPrecionConBs());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Qrcode", qrInputStream); // Pasar el InputStream del QR code
        parameters.put("logoEmpresa", new ByteArrayInputStream(logoImageBytes)); // Pasar el InputStream del logo
        parameters.put("dsProforma", new JRBeanCollectionDataSource(materiales));
        parameters.put("dsPreciototal", new JRBeanCollectionDataSource(precios));
        parameters.put("hashQR", hash); // Pasar el hash como parámetro
        parameters.put("precionConBs", boletas.get(0).getPrecionConBs());
        parameters.put("precionConBsLetras", precioLetras);
        parameters.put("cmmConT", boletas.get(0).getCmmConT());
        parameters.put("flujoConF", boletas.get(0).getFlujoConF());
        parameters.put("proformaConPF", boletas.get(0).getProformaConPF());
        parameters.put("fechaDestino", boletas.get(0).getFechaDestino());
        parameters.put("fechaActual", new java.util.Date());// Pasar el hash como parámetro
        parameters.put("pesoKg", materiales.get(0).getPesoKg());// Pasar el hash como parámetro
        parameters.put("volumenM3",materiales.get(0).getVolumenM3());// Pasar el hash como parámetro
        parameters.put("nombreCompleto",Unidadsolicitantes.get(0).getNombreCompleto());
        parameters.put("proyecto",Unidadsolicitantes.get(0).getProyecto());
        
        // Llenar el datasource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletas);

        // Cargar y compilar la plantilla del reporte
        String fileDirectory = request.getServletContext().getRealPath("/WEB-INF/reportes/reporteProforma.jasper");
        FileInputStream stream = new FileInputStream(new File(fileDirectory));
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporteTransporte.pdf");

        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        outStream.flush();
        outStream.close();
        stream.close();
    }
//------------------------------------genera boleta de entrega --------------------
    @RequestMapping("/generaComprobanteDeEntregaPDF")
    @ResponseBody
    public void generaComprobanteEntrega(HttpServletRequest request, HttpServletResponse response, @RequestParam int idBoleta) throws IOException, JRException, WriterException {
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

        // Convertir las firmas a InputStream
        // Convertir las firmas a BufferedImage con color azul
        byte[] firmaOrigenEntelBytes = Base64.getDecoder().decode(boleta.getFirmaOrigenEntel());
        byte[] firmaOrigenEmpresaBytes = Base64.getDecoder().decode(boleta.getFirmaOrigenEmpresa());
        byte[] firmaDestinoEntelBytes = Base64.getDecoder().decode(boleta.getFirmaDestinoEntel());
        byte[] firmaDestinoEmpresaBytes = Base64.getDecoder().decode(boleta.getFirmaDestinoEmpresa());
         
        BufferedImage firmaOrigenEntelImage = crearImagenConColor(firmaOrigenEntelBytes, Color.BLUE);
        BufferedImage firmaOrigenEmpresaImage = crearImagenConColor(firmaOrigenEmpresaBytes, Color.BLUE);
        BufferedImage firmaDestinoEntelImage = crearImagenConColor(firmaDestinoEntelBytes, Color.BLUE);
        BufferedImage firmaDestinoEmpresaImage = crearImagenConColor(firmaDestinoEmpresaBytes, Color.BLUE);

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
        parameters.put("cmmConT", boletas.get(0).getCmmConT());
        parameters.put("flujoConF", boletas.get(0).getFlujoConF());
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
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporteComprobanteEntrega.pdf");

        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        outStream.flush();
        outStream.close();
        stream.close();
    }

    private BufferedImage crearImagenConColor(byte[] firmaBytes, Color color) throws IOException {
        // Leer la imagen original desde el arreglo de bytes
        BufferedImage firmaImage = ImageIO.read(new ByteArrayInputStream(firmaBytes));
        
        // Crear una imagen para el trazado de la firma (máscara)
        BufferedImage maskImage = new BufferedImage(firmaImage.getWidth(), firmaImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D maskGraphics = maskImage.createGraphics();
        maskGraphics.drawImage(firmaImage, 0, 0, null);
        maskGraphics.dispose();
        
        // Crear una imagen final para aplicar el color
        BufferedImage coloredImage = new BufferedImage(firmaImage.getWidth(), firmaImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = coloredImage.createGraphics();
        
        // Dibujar el fondo transparente en la imagen final
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, coloredImage.getWidth(), coloredImage.getHeight());
        
        // Dibujar la firma original en la imagen final
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.drawImage(firmaImage, 0, 0, null);
        
        // Aplicar el color solo a los trazos de la firma usando la máscara
        g2d.setComposite(AlphaComposite.SrcAtop);
        g2d.setColor(color);
        g2d.drawImage(maskImage, 0, 0, null);
        
        g2d.dispose();
        return coloredImage;
    }








    private InputStream bufferedImageToInputStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

//------------------------------------genera boleta de entrega --------------------
   
   
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
}
