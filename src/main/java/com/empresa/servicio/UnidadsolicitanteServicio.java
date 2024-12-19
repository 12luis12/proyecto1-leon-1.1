package com.empresa.servicio;

import java.util.List;
import java.util.Optional;


import com.empresa.entidad.Unidadsolicitante;

public interface UnidadsolicitanteServicio {
	public abstract List<Unidadsolicitante> listarTodoLosUnidadsolicitante();
	public abstract Unidadsolicitante registraActualizaUnidadSolicitante(Unidadsolicitante obj);
	public abstract void eliminaUnidadsolicitante(int id);
	public abstract List<Unidadsolicitante> buscarPorNombreUnidadsolicitante(String filtro);
	public abstract Optional<Unidadsolicitante> buscarUnidadsolicitantePorId(int id);
	public abstract Unidadsolicitante guardarActualizarUnidadsolicitante(Unidadsolicitante unidadsolicitante);
	//public abstract Unidadsolicitante buscarPorDni(String dni);
	public abstract Unidadsolicitante findByNombreAndApellidounidadS(String nombre, String apellido);
	 public abstract List< Unidadsolicitante> listaParaUnidaSolicitante(int idBoleta);
}
