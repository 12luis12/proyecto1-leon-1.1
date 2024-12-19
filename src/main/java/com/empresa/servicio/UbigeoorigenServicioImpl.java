package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Ubigeoorigen;
import com.empresa.repositorio.UbigeoorigenRepositorio;
@Service
public class UbigeoorigenServicioImpl implements UbigeoorigenServicio{
    
	@Autowired
	private UbigeoorigenRepositorio repositorio;
	
	@Override
	public List<String> listaDepartamentos() {
		return repositorio.listaDepartamentos();
	}

	@Override
	public List<String> listaProvincias(String departamento) {
		return repositorio.listaProvincias(departamento);
	}

	@Override
	public List<String> listaMunicipios(String departamento, String provincia) {
		return repositorio.listaMunicipios(departamento, provincia);
	}

	@Override
	public List<Ubigeoorigen> listaAlmacenes(String departamento, String provincia, String municipio) {
		return repositorio.listaAlmacenes(departamento, provincia, municipio);
	}
    //*********para crud ubigeoorigen******************

	@Override
	public List<Ubigeoorigen> listarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Ubigeoorigen registraActualizaUbigeoorigen(Ubigeoorigen obj) {
		return repositorio.save(obj);
		
	}

	@Override
	public void eliminaUbigeoorigen(int id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public List<Ubigeoorigen> buscaPorNombre(String filtro) {
		return repositorio.buscarPorNombre(filtro);
	}

	@Override
	public Optional<Ubigeoorigen> buscarPorId(int id) {
		return repositorio.findById(id);
	}
	
	@Override
	public List<Ubigeoorigen> listaUbigeoorigen(int idBoleta) {
		return repositorio.listaUbigeoorigen(idBoleta);
	}

	@Override
	public List<Ubigeoorigen> listaParaUbigeoorigen(int idBoleta) {
	return repositorio.listaUbigeoorigen(idBoleta);
	}
}
