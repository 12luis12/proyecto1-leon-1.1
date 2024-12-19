package com.empresa.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "opcion")
@Getter
@Setter
public class Opcion {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idOpcion")
	private int idOpcion;
	private String nombre;
	private String ruta;
	private int estado;
	private int tipo;
	

}
