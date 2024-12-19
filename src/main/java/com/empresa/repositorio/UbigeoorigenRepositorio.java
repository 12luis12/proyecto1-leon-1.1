package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Ubigeoorigen;


public interface UbigeoorigenRepositorio extends JpaRepository<Ubigeoorigen, Integer> {

	@Query("select distinct x.departamento from Ubigeoorigen x")
	public abstract List<String> listaDepartamentos();
	
	@Query("select distinct x.provincia from Ubigeoorigen x where x.departamento=:var_dep")
	public abstract List<String> listaProvincias(@Param("var_dep")String departamento);
	
	@Query("select distinct x.municipio from Ubigeoorigen x where x.departamento=:var_dep and x.provincia=:var_pro")
	public abstract List<String> listaMunicipios(@Param("var_dep")String departamento, @Param("var_pro")String provincia);
	
	@Query("select  x from Ubigeoorigen x where x.departamento=:var_dep and x.provincia=:var_pro and x.municipio=:var_mun")
	public abstract List<Ubigeoorigen> listaAlmacenes(@Param("var_dep")String departamento, @Param("var_pro")String provincia, @Param("var_mun")String municipio);
	
	//aqui para el crud de ubigeoorigen eliminar listar actualizar
	@Query("select a from Ubigeoorigen a where departamento like :fil")
	public abstract List<Ubigeoorigen> buscarPorNombre(@Param("fil") String filtro);
	
	
	@Query("select o from Boleta b, Ubigeoorigen o where o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and b.idBoleta=?1")
	//@Query("select b from Boleta b where idBoleta=?1")
	public abstract List< Ubigeoorigen> listaUbigeoorigen(int idBoleta);
	
}
