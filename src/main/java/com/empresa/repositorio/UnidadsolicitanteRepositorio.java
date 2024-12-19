package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Material;
import com.empresa.entidad.Unidadsolicitante;

public interface UnidadsolicitanteRepositorio extends JpaRepository<Unidadsolicitante, Integer> {
	@Query("select a from Unidadsolicitante a where nombre like :fil")
	public abstract List<Unidadsolicitante> buscarPorNombreUnidadsolicitante(@Param("fil") String filtro);
	//@Query("select a from Unidadsolicitante a where a.dni like :var_nom")
	//public abstract Unidadsolicitante buscarPorDni(@Param("var_nom")String dni);
	
	@Query("select a from Unidadsolicitante a where a.nombre like :var_nom and a.apellido like :var_ape")
	public abstract Unidadsolicitante findByNombreAndApellidounidadS(@Param("var_nom")String nombre, @Param("var_ape")String apellido);
	
	//@Query("select m from Boleta b,Unidadsolicitante m where b.idBoleta=m.boleta.idBoleta and b.idBoleta=?1")
	@Query("select m from Boleta b,Unidadsolicitante m where m.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and b.idBoleta=?1")
	public abstract List< Unidadsolicitante> listaParaUnidaSolicitante(int idBoleta);
}
