package com.empresa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Ubigeodestino;
import com.empresa.repositorio.UbigeodestinoRepositorio;

@Service
public class UbigeodestinoServicioImpl implements UbigeodestinoServicio  {
    
	@Autowired
	private UbigeodestinoRepositorio repositorio;
	@Override
	public List<String> listaDepartamentos1() {
		return repositorio.listaDepartamentos1();
	}

	@Override
	public List<String> listaProvincias1(String departamento1) {
		return repositorio.listaProvincias1(departamento1);
	}

	@Override
	public List<String> listaMunicipios1(String departamento1, String provincia1) {
		return repositorio.listaMunicipios1(departamento1, provincia1);
	}

	@Override
	public List<Ubigeodestino> listaAlmacenes1(String departamento1, String provincia1, String municipio1) {
		return repositorio.listaAlmacenes1(departamento1, provincia1, municipio1);
	}

	@Override
	public List<Ubigeodestino> listaParaUbigeodestino(int idBoleta) {
	return repositorio.listaParaUbigeodestino(idBoleta);
	}

	

}
