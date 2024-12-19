package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.empresa.entidad.Material;
import com.empresa.entidad.Precio;

public interface MaterialServicio {
	public abstract List<Material> buscarPorEstado(int filtro);
	
	public abstract Material registraMaterial(Material obj);
	public abstract void eliminaMaterial(int id);
	 public abstract List<Material> listaMaterialEstadoCero();
	 //public abstract Material registrarTodosLosCeros(List<Material> myList );
	 public abstract Iterable<Material> registrarTodosLosCeros(Iterable<Material> myList );
	 void guardarCopiaEnAcumulador(Material material);
	 
	 public abstract List< Material> listaParaMaterial(int idBoleta);
	 
	 public abstract  Material listaMaterialPorId(int idMaterial);
	 public abstract Material guardarActualizarMaterial(Material material);

	public abstract Material findById(int id);
	public Optional<Material> buscarMaterialPorId(int id);
	

	
}
