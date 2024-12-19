package com.empresa.entidad;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "precio")
@Getter
@Setter
public class Precio {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idPrecio")
	private int idPrecio;
	@Column(name = "normalMinima", precision = 10, scale = 2)
	private BigDecimal normalMinima;
	 @Column(name = "normalM3", precision = 10, scale = 2)
	private BigDecimal normalM3;
	 @Column(name = "normalKgAdicional", precision = 10, scale = 2)
	private BigDecimal normalKgAdicional;
	private int tiempoNormal;
	@Column(name = "expresMinimo", precision = 10, scale = 2)
	private BigDecimal expresMinimo;

    @Column(name = "expresM3", precision = 10, scale = 2)
	private BigDecimal expresM3;

    @Column(name = "expresKgAdicional", precision = 10, scale = 2)
	private BigDecimal expresKgAdicional;

    @Column(name = "tiempoExpres")
	private int tiempoExpres;

    @Column(name = "nombreLugar")
	private String nombreLugar;
	//private Point coordenadas;
	
	
	/*@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUbigeodestino")
	private Ubigeodestino ubigeodestino;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUbigeoorigen")
	private Ubigeoorigen ubigeoorigen;*/
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUbigeodestino")
	private Ubigeodestino ubigeodestino;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUbigeoorigen")
	private Ubigeoorigen ubigeoorigen;
	
	 @JsonIgnore
		@OneToMany(mappedBy="precio")
		private List<Boleta> boletas;
	 
 
	 
	 
	 
}
