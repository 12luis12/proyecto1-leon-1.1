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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ubigeoorigen")
@Getter
@Setter
public class Ubigeoorigen {
	  @Id
	  @GeneratedValue(strategy= GenerationType.IDENTITY)
	  @Column(name = "idUbigeoorigen")
	  private int idUbigeoorigen;
	  
	  @Column(name = "departamento")
	  private String departamento;
	  
	  @Column(name = "provincia")
	  private String provincia;
	  
	  @Column(name = "municipio")
	  private String municipio;
	  
	  @Column(name = "almacen")
	  private String almacen;
	  
	    @JsonIgnore
		@OneToMany(mappedBy="ubigeoorigen")
		private List<Boleta> boletas;
	    
	    
	    @Transient
	    private String almacenDepartamento;

	    // Métodos para obtener el valor calculado
	    public String getAlmacenDepartamento() {
	        String almacen = (this.almacen != null) ? this.almacen : "";
	        String departamento = (this.departamento != null) ? this.departamento : "";
	        return almacen.concat("(").concat(departamento).concat(")").trim();
          }

	    // Este método no es necesario si solo necesitas el valor calculado
	    // y no estás almacenando el valor en un campo persistente
	    public void setAlmacenDepartamento(String almacenDepartamento) {
	        this.almacenDepartamento = almacenDepartamento;
	    }
	  
}
