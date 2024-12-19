package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.empresa.entidad.Unidadsolicitante;
import com.empresa.repositorio.UnidadsolicitanteRepositorio;

@Service
public class UnidadsolicitanteServicioImpl implements UnidadsolicitanteServicio {
	@Autowired
	private UnidadsolicitanteRepositorio repositorio;
	@Override
	public List<Unidadsolicitante> listarTodoLosUnidadsolicitante() {
		return repositorio.findAll();
	}

	@Override
	public Unidadsolicitante registraActualizaUnidadSolicitante(Unidadsolicitante obj) {
		return repositorio.save(obj);
	}

	@Override
	public void eliminaUnidadsolicitante(int id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public List<Unidadsolicitante> buscarPorNombreUnidadsolicitante(String filtro) {
		
		return repositorio.buscarPorNombreUnidadsolicitante(filtro);
	}

	@Override
	public Optional<Unidadsolicitante> buscarUnidadsolicitantePorId(int id) {
		return repositorio.findById(id);
	}

	@Override
	public Unidadsolicitante guardarActualizarUnidadsolicitante(Unidadsolicitante unidadsolicitante) {
		return repositorio.save(unidadsolicitante);
	}

	@Override
	public Unidadsolicitante findByNombreAndApellidounidadS(String nombre,String apellido) {
		return repositorio.findByNombreAndApellidounidadS(nombre, apellido);
	}
	@Override
	public List<Unidadsolicitante> listaParaUnidaSolicitante(int idBoleta) {
		return repositorio.listaParaUnidaSolicitante(idBoleta);
	}
}
