package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Precio;
import com.empresa.repositorio.PrecioRepositorio;

@Service
public class PrecioServicioImpl implements PrecioServicio {
	@Autowired
    private PrecioRepositorio repositorio;
	
	@Override
	public Precio insertaPrecio(Precio obj) {
		return repositorio.save(obj);
	}
	
	@Override
	public List<Precio> listaParaPrecio(int idBoleta) {
		return repositorio.listaParaPrecio(idBoleta);
	}

	@Override
	public boolean existeIdUbigeoorigenIdUbigeodestino(int idUbigeoorigen, int idUbigeodestino) {
	return repositorio.existeIdUbigeoorigenIdUbigeodestino(idUbigeoorigen, idUbigeodestino);
	}

	
	
	@Override
	public Precio insertaActualizaPrecio(Precio obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Precio> listaPrecios() {
	return repositorio.listaPrecios();
	}

	@Override
	public Optional<Precio> buscarPrecioPorId(int id) {
	return repositorio.findById(id);
	}

	@Override
	public void eliminaPrecio(int idPrecio) {
		repositorio.deleteById(idPrecio);
		
	}
}
