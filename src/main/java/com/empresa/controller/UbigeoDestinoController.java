package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Ubigeodestino;
import com.empresa.repositorio.UbigeodestinoRepositorio;

@Controller
public class UbigeoDestinoController {
	@Autowired
	private UbigeodestinoRepositorio destinoService;
	@RequestMapping("/listaDepartamentos1")
	@ResponseBody
	public List<String> verDepartamentos1() {
		
		return destinoService.listaDepartamentos1();
	}
	
	@RequestMapping("/listaProvincias1")
	@ResponseBody
	public List<String> verProvinicias(String departamento) {
		return destinoService.listaProvincias1(departamento);
	}
	
	@RequestMapping("/listaMunicipios1")
	@ResponseBody
	public List<String> verMunicipios(String departamento, String provincia) {
		return destinoService.listaMunicipios1(departamento, provincia);
	}
	
	@RequestMapping("/listaAlmacenes1")
	@ResponseBody
	public List<Ubigeodestino> verAlmacenes(String departamento, String provincia,String municipio) {
		return destinoService.listaAlmacenes1(departamento, provincia,municipio);
	}

}
