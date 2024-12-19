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
@Table(name = "rol")
@Getter
@Setter
public class Rol {

	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idRol")
	private int idRol;
	private String nombre;
	private int estado;
}
