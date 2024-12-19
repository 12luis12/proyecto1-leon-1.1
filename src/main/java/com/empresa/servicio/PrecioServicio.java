package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.empresa.entidad.Precio;

public interface PrecioServicio {
	public abstract Precio insertaPrecio(Precio obj);
	public abstract List< Precio> listaParaPrecio(int idBoleta);
	public abstract boolean existeIdUbigeoorigenIdUbigeodestino(int idUbigeoorigen, int idUbigeodestino );
	public abstract Precio insertaActualizaPrecio(Precio obj);
	
	public List<Precio> listaPrecios();
	
	
	public Optional<Precio> buscarPrecioPorId(int id);
	public void eliminaPrecio(int idPrecio);
	
}
