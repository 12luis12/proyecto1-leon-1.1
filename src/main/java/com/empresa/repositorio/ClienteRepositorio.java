package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Cliente;


public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

	@Query("select a from Cliente a where nombre like :fil")
	public abstract List<Cliente> buscarPorNombre(@Param("fil") String filtro);
	
	@Query("select a from Cliente a where a.nombre like :var_nom and a.apellido like :var_ape")
	public abstract Cliente findByNombreAndApellido1(@Param("var_nom")String nombre, @Param("var_ape")String apellido);
	
}
