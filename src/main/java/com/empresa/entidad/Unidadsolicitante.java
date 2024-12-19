package com.empresa.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unidadsolicitante")
@Getter
@Setter
public class Unidadsolicitante {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idUnidadsolicitante")
	private int idUnidadsolicitante;
	private String nombre;
	private String unidadSolicitante;
	private String proyecto;
	private String apellido;
	private String correo;
	private String celular;
	private String dni;
	@JsonIgnore
	@OneToMany(mappedBy="unidadsolicitante")
	private List<Boleta> boletas;
	
	
	@Transient
	private String datosCompletoUnidadSolicitante;

	public String getDatosCompletoUnidadSolicitante() {
	    String apellido = (this.apellido != null) ? this.apellido : "";
	    String nombre = (this.nombre != null) ? this.nombre : "";
	    String unidadSolicitante = (this.unidadSolicitante != null) ? this.unidadSolicitante : "";
	    String celular = (this.celular != null) ? this.celular : "";
	    String correo = (this.correo != null) ? this.correo : "";
	    String proyecto = (this.proyecto != null) ? this.proyecto : "";
	    return "<font color='red'>Nombre:</font> ".concat(apellido).concat(" ").concat(nombre).trim()
	           .concat("<br><font color='red'>Celular:</font> ").concat(celular)
	           .concat("<br><font color='red'>Correo:</font> ").concat(correo)
	           .concat("<br><font color='red'>unidadSolicitante:</font> ").concat(unidadSolicitante)
	           .concat("<br><font color='red'>proyecto:</font> ").concat(proyecto);
	}
	public void setDatosCompletoUnidadSolicitante(String datosCompletoUnidadSolicitante) {
	    this.datosCompletoUnidadSolicitante = datosCompletoUnidadSolicitante;
	
	}
	
	
	
	@Transient
	private String nombreCompleto;
	    
	public String getNombreCompleto() {
	    String apellido = (this.apellido != null) ? this.apellido : "";
	    String nombre = (this.nombre != null) ? this.nombre : "";
	    return apellido.concat("  ").concat(nombre).trim();
	}
	
	
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto=nombreCompleto;
	}
	    @PrePersist
	    @PreUpdate
	    public void prePersistAndUpdate() {
	        if (this.nombre == null) {
	            this.nombre = "-";
	        } else {
	            this.nombre = this.nombre.toLowerCase();
	        }

	        if (this.apellido == null) {
	            this.apellido = "-";
	        } else {
	            this.apellido = this.apellido.toLowerCase();
	        }
	        if (this.unidadSolicitante == null) {
	            this.unidadSolicitante = "-";
	        } else {
	            this.unidadSolicitante = this.unidadSolicitante.toLowerCase();
	        }
	        
	        if (this.proyecto == null) {
	            this.proyecto = "-";
	        } 
	        if (this.celular == null) {
	            this.celular = "-";
	        } 
	        
	        if (this.correo == null) {
	            this.correo = "-";
	        } 
	        
	        
	        
	        if (this.dni == null) {
	            this.dni = "-";
	        } 
	    }	
	
}
