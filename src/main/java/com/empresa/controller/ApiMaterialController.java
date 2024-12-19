package com.empresa.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.empresa.entidad.Material;

import com.empresa.servicio.MaterialServicio;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/servicio/Material")
public class ApiMaterialController  {
	@Autowired
	private MaterialServicio materialServicio;
	
	@GetMapping("/consultarMaterialEstado")
	public ResponseEntity<List<Material>> listaMaterialPorId() {
		log.info("METODO --> listaAlumnoPorNombre");
		return ResponseEntity.ok(materialServicio.listaMaterialEstadoCero());
	}
	
	
	@DeleteMapping("eliminaMaterial/{id}")
	public ResponseEntity<Material> eliminaMateriales(@PathVariable int id) {
		log.info("METODO --> elimina " + id);
		Optional<Material> obj = materialServicio.buscarMaterialPorId(id);
		if (obj.isPresent()) {
			materialServicio.eliminaMaterial(id);
			return ResponseEntity.ok(obj.get());
		} else {
			log.error("Id " + id + " no existe");
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("buscarMaterial2/{id}")
	public ResponseEntity<Material> buscarMaterialPorId(@PathVariable int id) {
	    log.info("METODO --> buscarPorId " + id);
	    try {
	        Optional<Material> obj = materialServicio.buscarMaterialPorId(id);
	        if (obj.isPresent()) {
	            return ResponseEntity.ok(obj.get());
	        } else {
	            log.error("Id " + id + " no existe");
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        log.error("Error inesperado al buscar material con id " + id, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	@GetMapping("listaMaterial/{idBoleta}")
	public ResponseEntity<List<Material>> buscarPorId(@PathVariable int idBoleta) {
	    log.info("METODO --> buscarPorId " + idBoleta);
	    List<Material> obj = materialServicio.listaParaMaterial(idBoleta);

	    // Return 200 OK with an empty list if no materials found
	    return ResponseEntity.ok(obj);
	}


	@PostMapping("registrarMaterial")
	public ResponseEntity<Material> registrar(@RequestBody Material obj) {
		log.info("METODO --> registrar");
	return ResponseEntity.ok(materialServicio.guardarActualizarMaterial(obj));
	}
	
}