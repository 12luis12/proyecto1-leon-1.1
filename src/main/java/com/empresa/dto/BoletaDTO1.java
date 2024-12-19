package com.empresa.dto;

import com.empresa.entidad.Boleta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletaDTO1 {
	    private int idBoleta;
	    private String flujo;
	    private String cmm;
	    private String departamentoUbigeoOrigen;
		private String departamentoUbigeoDestino;
		private String nombreCompletoUsuario;
	    
	    public static BoletaDTO1 fromBoleta1(Boleta boleta) {
	        BoletaDTO1 dto = new BoletaDTO1();
	       
	        dto.setIdBoleta(boleta.getIdBoleta());
	        dto.setCmm(boleta.getCmm());
	        dto.setFlujo(boleta.getFlujo());
	        
	        dto.setDepartamentoUbigeoOrigen(boleta.getUbigeoorigen().getDepartamento());
	        dto.setDepartamentoUbigeoDestino(boleta.getUbigeodestino().getDepartamento());
	        dto.setNombreCompletoUsuario(boleta.getUsuario2().getNombreCompleto());
	                    
	        return dto;
	    }
}
