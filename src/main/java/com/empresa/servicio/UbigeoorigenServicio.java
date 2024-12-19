package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.empresa.entidad.Ubigeoorigen;
import com.empresa.entidad.Unidadsolicitante;


public interface UbigeoorigenServicio {
	public abstract List<String> listaDepartamentos();
	public abstract List<String> listaProvincias(String departamento);
	public abstract List<String> listaMunicipios(String departamento, String provincia);
	public abstract List<Ubigeoorigen> listaAlmacenes(String departamento, String provincia, String municipio);
	//aqui para el crud de ubigeoorigen eliminar listar actualizar
	public abstract List<Ubigeoorigen> listarTodos();
	public abstract Ubigeoorigen registraActualizaUbigeoorigen(Ubigeoorigen obj);
	public abstract void eliminaUbigeoorigen(int id);
	public abstract List<Ubigeoorigen> buscaPorNombre(String filtro);
	public abstract Optional<Ubigeoorigen> buscarPorId(int id);
	
	public abstract List<Ubigeoorigen> listaUbigeoorigen(int idBoleta);
	 public abstract List< Ubigeoorigen> listaParaUbigeoorigen(int idBoleta);

	
}
