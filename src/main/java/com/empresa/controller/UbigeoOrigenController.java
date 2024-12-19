package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Ubigeoorigen;
import com.empresa.servicio.UbigeoorigenServicio;


@Controller
public class UbigeoOrigenController {
	@Autowired
	private UbigeoorigenServicio ubigeoService;
	
	@RequestMapping("/listaDepartamentos")
	@ResponseBody
	public List<String> verDepartamentos() {
		
		return ubigeoService.listaDepartamentos();
	}
	
	@RequestMapping("/listaProvincias")
	@ResponseBody
	public List<String> verProvinicias(String departamento) {
		return ubigeoService.listaProvincias(departamento);
	}
	
	@RequestMapping("/listaMunicipios")
	@ResponseBody
	public List<String> verMunicipios(String departamento, String provincia) {
		return ubigeoService.listaMunicipios(departamento, provincia);
	}
	
	@RequestMapping("/listaAlmacenes")
	@ResponseBody
	public List<Ubigeoorigen> verAlmacenes(String departamento, String provincia,String municipio) {
		return ubigeoService.listaAlmacenes(departamento, provincia,municipio);
	}
}
