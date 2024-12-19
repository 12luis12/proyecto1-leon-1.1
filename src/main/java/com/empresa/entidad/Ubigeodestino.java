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
@Table(name = "ubigeodestino")
@Getter
@Setter
public class Ubigeodestino {
	  @Id
	  @GeneratedValue(strategy= GenerationType.IDENTITY)
	  @Column(name = "idUbigeodestino")
	  private int idUbigeodestino;
	  @Column(name = "departamento")
	  private String departamento;
	  
	  @Column(name = "provincia")
	  private String provincia;
	  
	  @Column(name = "municipio")
	  private String municipio;
	  
	  @Column(name = "almacen")
	  private String almacen;
	  
	  @JsonIgnore
		@OneToMany(mappedBy="ubigeodestino")
		private List<Boleta> boletas;
	  
	  @Transient
		private String almacenDepartamento;
		
	//	public String getAlmacenDepartamento() {
		//	 return almacen.concat(" (").concat(departamento).concat(")");
		//}
		
	 

	    // Métodos para obtener el valor calculado
	    public String getAlmacenDepartamento() {
	        String almacen = (this.almacen != null) ? this.almacen : "";
	        String departamento = (this.departamento != null) ? this.departamento : "";
	        return almacen.concat("(").concat(departamento).concat(")").trim();
        }
		public void setAlmacenDepartamento(String almacenDepartamento) {
			this.almacenDepartamento=almacenDepartamento;
		}
}
