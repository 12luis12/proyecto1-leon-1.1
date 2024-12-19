package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Ubigeodestino;
import com.empresa.entidad.Ubigeoorigen;
import com.empresa.entidad.Unidadsolicitante;


public interface UbigeodestinoRepositorio extends JpaRepository<Ubigeodestino, Integer>{

	
		@Query("select distinct x.departamento from Ubigeodestino x")
		public abstract List<String> listaDepartamentos1();
		
		@Query("select distinct x.provincia from Ubigeodestino x where x.departamento=:var_dep")
		public abstract List<String> listaProvincias1(@Param("var_dep")String departamento);
		
		@Query("select distinct x.municipio from Ubigeodestino x where x.departamento=:var_dep and x.provincia=:var_pro")
		public abstract List<String> listaMunicipios1(@Param("var_dep")String departamento, @Param("var_pro")String provincia);
		
		@Query("select  x from Ubigeodestino x where x.departamento=:var_dep and x.provincia=:var_pro and x.municipio=:var_mun")
		public abstract List<Ubigeodestino> listaAlmacenes1(@Param("var_dep")String departamento, @Param("var_pro")String provincia, @Param("var_mun")String municipio);

		
		
		
		
		
		
		
		@Query("select o from Boleta b, Ubigeodestino o where o.idUbigeodestino=b.ubigeodestino.idUbigeodestino and b.idBoleta=?1")
		public abstract List< Ubigeodestino> listaParaUbigeodestino(int idBoleta);
		}
