package com.empresa.entidad;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {
	
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private int idCliente;
	private String nombre;
	private String apellido;
	private String telefono;
	private String celular;
	private String correo;
	private Byte estado;
	private String dni;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //guarda fecha actual
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaRegistro = new Date();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<Boleta> boletas;
	//------------
	//@JsonIgnore
	//@OneToMany(mappedBy="cliente")
	
	//private List<Boleta> boletass;
	
	
	@Transient
	private String datosCompletoCliente;

	public String getDatosCompletoCliente() {
	    String apellido = (this.apellido != null) ? this.apellido : "";
	    String nombre = (this.nombre != null) ? this.nombre : "";
	    String celular = (this.celular != null) ? this.celular : "";
	    String correo = (this.correo != null) ? this.correo : "";


	    return "<font color='red'>Nombre:</font> ".concat(apellido).concat(" ").concat(nombre).trim()
	           .concat("<br><font color='red'>Celular:</font> ").concat(celular)
	           .concat("<br><font color='red'>Correo:</font> ").concat(correo);
	}
	public void setDatosCompletoCliente(String datosCompletoCliente) {
	    this.datosCompletoCliente = datosCompletoCliente;
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
	        
	        if (this.telefono == null) {
	            this.telefono = "-";
	        } 
	        if (this.celular == null) {
	            this.celular = "-";
	        } 
	        
	        if (this.correo == null) {
	            this.correo = "-";
	        } 
	        
	        if (this.estado == null) {
	            this.estado =0;
	        } 
	        
	        if (this.dni == null) {
	            this.dni = "-";
	        } 
	    }
	
}
