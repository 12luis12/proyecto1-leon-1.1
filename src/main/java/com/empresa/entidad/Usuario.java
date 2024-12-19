package com.empresa.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {

	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private int idUsuario=0;
	private String nombre;
	private String apellido;
	private String dni;
	private String login;
	private String password;
	private String correo;
	
	/*@Transient
	private String nombreCompleto;
	
	public String getNombreCompleto() {
		return nombre.concat("  ").concat(apellido);
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto=nombreCompleto;
	}*/
	
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
	
	
	 @JsonIgnore
		@OneToMany(mappedBy="usuario")
		private List<Boleta> boletas;
	 
	 
	 @JsonIgnore
		@OneToMany(mappedBy="usuario2")
		private List<Boleta> boletass;
	 
	 

}
