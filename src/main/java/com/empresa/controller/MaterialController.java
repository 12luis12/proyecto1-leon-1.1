package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Material;
import com.empresa.servicio.MaterialServicio;

@Controller

public class MaterialController {
	@Autowired
	 private MaterialServicio materialServicio;
	
	
	@RequestMapping("/consultaMaterial")
	@ResponseBody()
	public List<Material> listarMaterial( int filtro){
	List<Material> resul=materialServicio.listaParaMaterial(filtro);
	return resul;
	}
	
	
	@GetMapping("/buscarMaterial")
	 @ResponseBody
	         public Material listarMaterial1(@RequestParam int id){
	         return materialServicio.listaMaterialPorId(id);
	    }
	
	
	
	
	
	
	  @PostMapping("/guardarMaterialActualizado")
	  @ResponseBody
	 public Material guardarMaterial(@RequestBody Material obj) {
    return materialServicio.guardarActualizarMaterial(obj);
     }
	
	  @GetMapping("/buscarMaterial1")
	    @ResponseBody
	    public Material buscarMaterial(@RequestParam int id) {
	        return materialServicio.findById(id);
	    }
	  
	  
	  @ResponseBody
		@RequestMapping("/guardarMaterialActualizado1")
		public HashMap<String, Object> registrar(Material obj){
			HashMap<String, Object> salida= new HashMap<>();
			Material objSalida=materialServicio.guardarActualizarMaterial(obj);
			if(objSalida==null) {
				salida.put("MENSAJE", "registro erroneo");
			}else {
				salida.put("MENSAJE", "registro exitoso");
				
			}
			return salida;
		}
	
}
