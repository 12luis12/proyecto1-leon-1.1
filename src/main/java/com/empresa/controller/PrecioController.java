package com.empresa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Precio;
import com.empresa.servicio.PrecioServicio;

@Controller
public class PrecioController {
	@Autowired
	private PrecioServicio servicio;
	
	@ResponseBody
	@RequestMapping("/registroCliente")
	public HashMap<String, Object> registrar(Precio obj){
		HashMap<String, Object> salida= new HashMap<>();
		Precio objSalida=servicio.insertaPrecio(obj);
		if(objSalida==null) {
			salida.put("MENSAJE", "registro erroneo");
		}else {
			salida.put("MENSAJE", "registro exitoso");
			
		}
		return salida;
	}
}
