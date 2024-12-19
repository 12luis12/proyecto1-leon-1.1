package com.empresa.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entidad.Boleta;
import com.empresa.entidad.Material;
import com.empresa.entidad.Precio;
import com.empresa.servicio.BoletaServicio;
import com.empresa.servicio.MaterialServicio;
import com.empresa.servicio.PrecioServicio;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/servicio/precio")
public class ApiPrecioController  {
	@Autowired
	private BoletaServicio boletaServicio;
	@Autowired
	private PrecioServicio precioServicio;
	@Autowired
	private MaterialServicio materialServicio;
	@GetMapping("/listaPrecios")
	public ResponseEntity<List<Precio>> listaPrecio() {
		log.info("METODO --> listaAlumno");
		return ResponseEntity.ok(precioServicio.listaPrecios());
	}
	
	@DeleteMapping("eliminaPrecio/{id}")
	public ResponseEntity<Precio> elimina(@PathVariable int id) {
		log.info("METODO --> elimina " + id);
		Optional<Precio> obj = precioServicio.buscarPrecioPorId(id);
		if (obj.isPresent()) {
			precioServicio.eliminaPrecio(id);
			return ResponseEntity.ok(obj.get());
		} else {
			log.error("Id " + id + " no existe");
			return ResponseEntity.notFound().build();
		}
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<Precio> buscarPorId(@PathVariable int id) {
		log.info("METODO --> buscarPorId " + id);
		Optional<Precio> obj = precioServicio.buscarPrecioPorId(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok(obj.get());
		} else {
			log.error("Id " + id + " no existe");
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/calcularPrecioFinal/{idBoleta}")
	public ResponseEntity<?> ejecutarMetodoParaCalcularPrecio(@PathVariable int idBoleta) {
	    log.info("METODO --> buscarPorId " + idBoleta);
	    
	    // Lógica para buscar el objeto por ID (usando un repositorio, por ejemplo)
	     calculaPrecioDeCadaCmm(idBoleta);
	    // Optional<Precio> obj = precioServicio.buscarPrecioPorId(idBoleta);
	     Optional<Boleta> obj = boletaServicio.buscarPoID(idBoleta);
		  
	    if (obj.isPresent()) {
	        return ResponseEntity.ok(obj.get());
	    } else {
	        log.error("idBoleta " + idBoleta + " no existe");
	        return ResponseEntity.notFound().build();
	    }
	}
	
	public void calculaPrecioDeCadaCmm(int idBoleta) {
		    List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
		    List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);
		    List<Precio> precios = precioServicio.listaParaPrecio(idBoleta);

		    // Verificar que las listas no estén vacías
		    if (boletas.isEmpty() || materiales.isEmpty() || precios.isEmpty()) {
		    	throw new RuntimeException("Datos no encontrados para idBoleta: " + idBoleta);
		    }

		    BigDecimal preciofijado = BigDecimal.ZERO;
		    BigDecimal volumen = materiales.get(0).getVolumen();
		    Byte cobrarpor = materiales.get(0).getCobrarpor();
		    Byte prioridadtransporte = boletas.get(0).getPrioridadtransporte();

		    BigDecimal normalminima = precios.get(0).getNormalMinima();
		    BigDecimal normalm3 = precios.get(0).getNormalM3();
		    BigDecimal normalkg = precios.get(0).getNormalKgAdicional();

		    BigDecimal expresminima = precios.get(0).getExpresMinimo();
		    BigDecimal expresm3 = precios.get(0).getExpresM3();
		    BigDecimal expreskg = precios.get(0).getExpresKgAdicional();

		    BigDecimal CINCO_CIENTOS = new BigDecimal("500");

		    // Lógica de cálculo
		    if (prioridadtransporte == 0) { // transporte por carretera
		        if (cobrarpor == 0) { // cobrar por kg
		            BigDecimal preciofinal = BigDecimal.ZERO;
		            for (Material material : materiales) {
		                BigDecimal peso = material.getPeso();
		                preciofinal = preciofinal.add(peso);
		            }
		            BigDecimal calculo = preciofinal.subtract(CINCO_CIENTOS);
		            if (preciofinal.compareTo(CINCO_CIENTOS) <= 0) {
		                preciofijado = normalminima;
		            } else {
		                preciofijado = normalminima.add(calculo.multiply(normalkg));
		            }
		        } else if (cobrarpor == 1) { // cobrar por m3
		            preciofijado = volumen.multiply(normalm3);
		        }
		    } else if (prioridadtransporte == 1) { // transporte por avión
		        if (cobrarpor == 0) { // cobrar por kg
		            BigDecimal preciofinal = BigDecimal.ZERO;
		            for (Material material : materiales) {
		                BigDecimal peso = material.getPeso();
		                preciofinal = preciofinal.add(peso);
		            }
		            BigDecimal calculo = preciofinal.subtract(CINCO_CIENTOS);
		            if (preciofinal.compareTo(CINCO_CIENTOS) <= 0) {
		                preciofijado = expresminima;
		            } else {
		                preciofijado = expresminima.add(calculo.multiply(expreskg));
		            }
		        } else if (cobrarpor == 1) { // cobrar por m3
		            preciofijado = volumen.multiply(expresm3);
		        }
		    }

		    // Actualizar y guardar la boleta
		    Boleta primeraBoleta = boletas.get(0);
		    primeraBoleta.setPrecioServicio(preciofijado);
		    boletaServicio.guardarActualizarBoleta(primeraBoleta);
			
	}
	
}
