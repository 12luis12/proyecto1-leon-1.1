package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Clientedestino;

public interface ClientedestinoRepositorio extends JpaRepository<Clientedestino, Integer> {
	@Query("select a from Clientedestino a where nombre like :fil")
	public abstract List<Clientedestino> buscarPorNombre(@Param("fil") String filtro);
}
