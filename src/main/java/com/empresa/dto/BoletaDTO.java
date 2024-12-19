package com.empresa.dto;

import com.empresa.entidad.Boleta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletaDTO {
    private int idBoleta;
    private int idUsuario2;
    private int idUsuario;
    private String flujo;
    private Byte estadoFirma;
    private Byte botonConsolidacion;
    private int numguia;
    private String cmm;
    private String descripcion;
    
   
    private byte[] firmaOrigenEmpresa;
    private byte[] firmaDestinoEmpresa;
    private byte[] firmaOrigenEntel;
    private byte[] firmaDestinoEntel;
    
    private String observacionOrigenEmpresa;
	private String observacionDestinoEmpresa;
	private String observacionOrigenEntel;
	private String observacionDestinoEntel;
	private String departamentoUbigeoOrigen;
	private String departamentoUbigeoDestino;
	private String nombreClienteOrigen;
	private String nombreClienteDestino;
	private String celularClienteOrigen;
	private String celularClienteDestino;
   

    public static BoletaDTO fromBoleta(Boleta boleta) {
        BoletaDTO dto = new BoletaDTO();
        Byte estadoFirma = boleta.getEstadoFirma();
        dto.setIdBoleta(boleta.getIdBoleta());
        dto.setCmm(boleta.getCmm());
        dto.setFlujo(boleta.getFlujo());
        dto.setEstadoFirma(boleta.getEstadoFirma());
        dto.setNumguia(boleta.getNumguia());
        dto.setDescripcion(boleta.getDescripcion());
        dto.setBotonConsolidacion(boleta.getBotonConsolidacion());
       
          if(estadoFirma==0) {
        	  dto.setFirmaOrigenEmpresa(boleta.getFirmaOrigenEmpresa());
        	  dto.setFirmaOrigenEntel(boleta.getFirmaOrigenEntel());
        	  dto.setObservacionOrigenEmpresa(boleta.getObservacionOrigenEmpresa());
        	  dto.setObservacionOrigenEntel(boleta.getObservacionOrigenEntel());
          }else if(estadoFirma==1) {
        	  dto.setFirmaDestinoEmpresa(boleta.getFirmaDestinoEmpresa());
        	  dto.setFirmaDestinoEntel(boleta.getFirmaDestinoEntel());
        	  dto.setObservacionDestinoEmpresa(boleta.getObservacionDestinoEmpresa());
        	  dto.setObservacionDestinoEntel(boleta.getObservacionDestinoEntel());
          }else {
        	  
          }
        // Condicional para incluir campos de firma solo si son null
      /*  if (ImageUtils.isPNG(boleta.getFirmaOrigenEmpresa())) {
            dto.setFirmaOrigenEmpresa(boleta.getFirmaOrigenEmpresa());
            
        }
        if (ImageUtils.isPNG(boleta.getFirmaDestinoEmpresa())) {
            dto.setFirmaDestinoEmpresa(boleta.getFirmaDestinoEmpresa());
        }
        if (ImageUtils.isPNG(boleta.getFirmaOrigenEntel())) {
            dto.setFirmaOrigenEntel(boleta.getFirmaOrigenEntel());
        }
        if (ImageUtils.isPNG(boleta.getFirmaDestinoEntel())) {
            dto.setFirmaDestinoEntel(boleta.getFirmaDestinoEntel());
        }*/
         dto.setDepartamentoUbigeoOrigen(boleta.getUbigeoorigen().getDepartamento());
         dto.setDepartamentoUbigeoDestino(boleta.getUbigeodestino().getDepartamento());
         dto.setNombreClienteOrigen(boleta.getCliente().getNombreCompleto());
         dto.setNombreClienteDestino(boleta.getClientedestino().getNombreCompleto());
         dto.setCelularClienteOrigen(boleta.getCliente().getCelular());
         dto.setCelularClienteDestino(boleta.getClientedestino().getCelular());
        dto.setIdUsuario2(boleta.getUsuario2().getIdUsuario());
         dto.setIdUsuario(boleta.getUsuario().getIdUsuario());
	        
        return dto;
    }

    // Getters y Setters para los campos
}
