package com.empresa.entidad;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "material")
@Getter
@Setter
public class Material {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idMaterial")//,nullable = true, insertable = false, updatable = false)
	private int idMaterial;
	private BigDecimal peso;
	private BigDecimal volumen;
	private BigDecimal cantidad;
	private String descripcion;
	private String observaciones;
	private String acumulador;
	//@Column(columnDefinition = "TINYINT(1)")
	private int estado;
	//private String estado;
	private Byte cobrarpor;
	
	
	@Transient
	private String datosDeTodoElMateriales;

	public String getDatosDeTodoElMateriales() {
	    String descripcion = (this.descripcion != null) ? this.descripcion : "";
	    BigDecimal cantidad = (this.cantidad != null) ? this.cantidad : BigDecimal.ZERO;
	    String peso = (this.pesoKg != null) ? this.pesoKg : "";
	    String volumen = (this.volumenM3 != null) ? this.volumenM3 : "";
	    String observaciones = (this.observaciones != null) ? this.observaciones : "";
	    return "<font color='red'>Descripción:</font> " + descripcion +
	            "<br><font color='red'>Cantidad:</font> " + cantidad.toString() +
	            "<br><font color='red'>Peso:</font> " + peso +
	            "<br><font color='red'>Volumen:</font> " + volumen +
	            "<br><font color='red'>Observaciones:</font> " + observaciones;	}
	public void setDatosDeTodoElMateriales(String datosDeTodoElMateriales) {
	    this.datosDeTodoElMateriales = datosDeTodoElMateriales;
	
	}
	
	
	
	@Transient
	private String pesoKg;
	
	public String getPesoKg() {
		 return peso.toString().concat(" Kg");
	}
	public void setPesoKg(String pesoKg) {
		this.pesoKg=pesoKg;
	}
	@Transient
	private String volumenM3;
	
	public String getVolumenM3() {
		 return volumen.toString().concat(" M3");
	}
	public void setVolmenM3(String volumenM3) {
		this.volumenM3=volumenM3;
	}
	/*@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="idBoleta")//,nullable = false, insertable = false, updatable = false)
	@JoinColumn(name="idBoleta")//,nullable = false, insertable = false, updatable = false)
		private Boleta boleta; */
	
	 @ManyToOne
	 @JoinColumn(name="idBoleta")
	 private Boleta boleta;
	
	@PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {
        if (this.descripcion == null) {
            this.descripcion = "-";
        } else {
        	 this.descripcion = justificarTexto(this.descripcion, 60);
	     	   this.descripcion = this.descripcion.toLowerCase();
        }

        if (this.observaciones == null) {
            this.observaciones = "-";
        } else {
        	 this.observaciones = justificarTexto(this.observaciones, 60);
     	    this.observaciones = this.observaciones.toLowerCase();
        }
       
	} 
        private String justificarTexto(String texto, int ancho) {
	        String[] palabras = texto.split("\\s+");
	        StringBuilder textoJustificado = new StringBuilder();
	        StringBuilder lineaActual = new StringBuilder();
	        int longitudLinea = 0;

	        for (String palabra : palabras) {
	            if (longitudLinea + palabra.length() + 1 > ancho) {
	                textoJustificado.append(justificarLinea(lineaActual.toString().trim(), ancho)).append("\n");
	                lineaActual = new StringBuilder();
	                longitudLinea = 0;
	            }
	            lineaActual.append(palabra).append(" ");
	            longitudLinea += palabra.length() + 1;
	        }
	        // Añadir la última línea
	        if (lineaActual.length() > 0) {
	            textoJustificado.append(lineaActual.toString().trim());
	        }

	        return textoJustificado.toString();
	    }

	    private String justificarLinea(String linea, int ancho) {
	        String[] palabras = linea.split("\\s+");
	        if (palabras.length == 1) {
	            return linea;  // No se puede justificar una sola palabra
	        }
	        int espaciosTotales = ancho - linea.replace(" ", "").length();
	        int espaciosEntrePalabras = palabras.length - 1;
	        int espacios = espaciosTotales / espaciosEntrePalabras;
	        int espaciosExtra = espaciosTotales % espaciosEntrePalabras;

	        StringBuilder lineaJustificada = new StringBuilder();
	        for (int i = 0; i < palabras.length; i++) {
	            lineaJustificada.append(palabras[i]);
	            if (i < palabras.length - 1) {
	                for (int j = 0; j <= espacios; j++) {
	                    lineaJustificada.append(" ");
	                }
	                if (i < espaciosExtra) {
	                    lineaJustificada.append(" ");
	                }
	            }
	        }
	        return lineaJustificada.toString();
	    }
   
	
}
