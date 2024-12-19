package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Material;

public interface MaterialRepositorio extends JpaRepository<Material, Integer> {
	@Query("select  x from Material x where estado like:fil")
	public abstract List<Material> buscarPorEstado(@Param("fil")int filtro);
	
	@Query("select m from Material m where m.estado=0")
	 public abstract List<Material> listaMaterialEstadoCero();
	//@Query(value="select * from boleta b,precio p where b.idUbigeoorigen=p.idUbigeoorigen and b.idUbigeodestino=p.idUbigeodestino and b.idBoleta=?1",nativeQuery = true)
	@Query("select m from Boleta b,Material m where b.idBoleta=m.boleta.idBoleta and b.idBoleta=?1")
	public abstract List< Material> listaParaMaterial(int idBoleta);
	@Query("select m from Material m where m.idMaterial = ?1")
	//@Query("select m from Material m, Boleta b where b.idBoleta=m.boleta.idBoleta and m.idMaterial = ?1")
	public abstract Material listaMaterialPorId(int idMaterial);
	
}
