package com.empresa.entidad;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "boleta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Boleta {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idBoleta")
	private int idBoleta;
	private String flujo;
	private String cmm;
	private Integer numguia;
	
	 
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //guarda fecha actual
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaRegistro = new Date();
     
	@Column(name = "fechaOrigen") //aqui tienes que introducirlo por el input
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaOrigen;
	
	@Column(name = "fechaDestino") //aqui tienes que introducirlo por el input
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaDestino;
	
	private Byte tipoTransporte;
	private Integer proforma;
	@Lob
	@Column(name = "firmaOrigenEmpresa", columnDefinition="BLOB")
	private byte[] firmaOrigenEmpresa;
	@Lob
	@Column(name = "firmaDestinoEmpresa", columnDefinition="BLOB")
	private byte[] firmaDestinoEmpresa;
	@Lob
	@Column(name = "firmaOrigenEntel", columnDefinition="BLOB")
	private byte[] firmaOrigenEntel;
	@Lob
	@Column(name = "firmaDestinoEntel", columnDefinition="BLOB")
	private byte[] firmaDestinoEntel;
	
	private String observacionOrigenEmpresa;
	private String observacionDestinoEmpresa;
	private String observacionOrigenEntel;
	private String observacionDestinoEntel;
	
	private Byte[] fotoOrigen;
	private Byte[] fotoDestino;
	
	private BigDecimal precioServicio;
	private String registrarLocalizador;
	private String muestraLocalizador;
	private Byte prioridadtransporte;
	private Byte botonConsolidacion;
	private Byte estadoFirma;
	
	
	 @Column(name = "acumulador", columnDefinition = "TEXT")
	private String acumulador;
	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;
	
	
	  
	

	
	
	
	
	
	//relcion clientes
	 @ManyToOne
	 @JoinColumn(name="idCliente")
	 private Cliente cliente;
	
	    @JsonIgnore
		@OneToMany(mappedBy="boleta")
		private List<Material> materiales;
	
	@ManyToOne
	@JoinColumn(name="idClientedestino")//,nullable = false, insertable = false, updatable = false)
	private Clientedestino clientedestino;
	
	//-------------------relacion boleta material 
   // @JsonIgnore
	//@OneToMany(mappedBy="boleta")
	//private List<Material> materiales;

//_____________________________
	 @ManyToOne
	 @JoinColumn(name="idUnidadsolicitante")
	 private Unidadsolicitante unidadsolicitante;
	 
	
	 
//ubigeo origen destino 
	 
	 @ManyToOne
	 @JoinColumn(name="idUbigeoorigen")
	 private Ubigeoorigen ubigeoorigen;
	 
	 @ManyToOne
	 @JoinColumn(name="idUbigeodestino")
	 private Ubigeodestino ubigeodestino;

	 @ManyToOne
	 @JoinColumn(name="idUsuario",referencedColumnName = "idUsuario")
	 private Usuario usuario;
	 
	 @ManyToOne
	 @JoinColumn(name = "idUsuario2", referencedColumnName = "idUsuario")
	 private Usuario usuario2;
	 
	 @ManyToOne
	 @JoinColumn(name="idCamion")
	 private Camion camion;
	 
	 
	 @ManyToOne
	 @JoinColumn(name="idPrecio")
	 private Precio precio;
	 
	 @Transient
		private String flujoConF;
		
		public String getFlujoConF() {
			 //return "F-"+flujo.toString();
			  return flujo != null ? "F-" + flujo.toString() : null;
		}
		public void setFlujoConF(String flujoConF) {
			this.flujoConF=flujoConF;
		}
		
		
		@Transient
		private String proformaConPF;
		
		public String getProformaConPF() {
		   // return proforma != 0 ? "PF-" + String.format("%06d", proforma) : null;
		    return proforma != null && proforma != 0 ? "PF-" + String.format("%06d", proforma) : "Sin Proforma";
		}

		public void setProformaConPF(String proformaConPF) {
			this.proformaConPF=proformaConPF;
		}
		
		@Transient
		private String numguiaConNg;
		
		public String getNumguiaConNg() {
		   // return numguia != 0 ? "NG-" + String.format("%06d", numguia) : null;
		    return numguia != null && numguia != 0 ? "NG-" + String.format("%06d", numguia) : "Sin guia";
			
		}

		public void setNumguiaConNg(String numguiaConNg) {
			this.numguiaConNg=numguiaConNg;
		}
		
		@Transient
		private String cmmConT;
		
		public String getCmmConT() {
			 //return "T-"+cmm.toString();
			 return cmm != null ? "T-" + cmm.toString() : null;
		}
		public void setCmmConT(String cmmConT) {
			this.cmmConT=cmmConT;
		}
	 /*@Transient
		private String precionConBs;
		
		public String getPrecionConBs() {
			 //return precioServicio.toString();
			return precioServicio.toString().concat(" Bs");
		}
		public void setPrecionConBs(String precionConBs) {
			this.precionConBs=precionConBs;
		}*/
		@Transient
		private String precionConBs;

		public String getPrecionConBs() {
		    if (precioServicio == null) {
		        return "Precio no disponible";
		    }
		    return precioServicio.toString().concat(" Bs");
		}

		public void setPrecionConBs(String precionConBs) {
		    this.precionConBs = precionConBs;
		}

	 
	 
	 
	 

	    @PrePersist
	    @PreUpdate
	    public void prePersistAndUpdate() {
	        if (this.observacionOrigenEmpresa == null) {
	            this.observacionOrigenEmpresa = "-";
	        } else {
	        	 this.observacionOrigenEmpresa = justificarTexto(this.observacionOrigenEmpresa, 40);
		     	  
	        	//this.observacionOrigen = formatearTexto(this.observacionOrigen);
	     	    this.observacionOrigenEmpresa = this.observacionOrigenEmpresa.toLowerCase();
	        }

	        if (this.observacionDestinoEmpresa == null) {
	            this.observacionDestinoEmpresa = "-";
	        } else {
	        	 this.observacionDestinoEmpresa = justificarTexto(this.observacionDestinoEmpresa, 40);
	     	    this.observacionDestinoEmpresa = this.observacionDestinoEmpresa.toLowerCase();
	        }
	       
	        
	        if (this.numguia == null) {
	            this.numguia = 0;
	        } 
	        if (this.proforma == null) {
	            this.proforma = 0;
	        } 
	        
	        if (this.registrarLocalizador == null) {
	            this.registrarLocalizador = "-";
	        } 
	        
	        
	        
	        if (this.muestraLocalizador == null) {
	            this.muestraLocalizador = "-";
	        } 
	        
	       
	    }	
	    
	    
	   

	    private String formatearTexto(String texto) {
	        String[] palabras = texto.split("\\s+");
	        StringBuilder textoFormateado = new StringBuilder();
	        int contador = 0;

	        for (String palabra : palabras) {
	            textoFormateado.append(palabra).append(" ");
	            contador++;
	            if (contador == 3) {
	                textoFormateado.append("\n");
	                contador = 0;
	            }
	        }

	        return textoFormateado.toString().trim();
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
