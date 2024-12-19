package com.empresa.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "camion")
@Getter
@Setter
public class Camion {


	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idCamion")
	private int idCamion;
	private String nombre;
	private String apellido;
	private String placa;
	private String tipoCamion;
	private String modelo;
	private String capacidad;
	
	@JsonIgnore
	@OneToMany(mappedBy="camion")
	private List<Boleta> boletas;
	
}
