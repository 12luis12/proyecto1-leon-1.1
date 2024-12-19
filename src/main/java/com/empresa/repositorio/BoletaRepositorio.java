package com.empresa.repositorio;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Boleta;





public interface BoletaRepositorio extends JpaRepository<Boleta, Integer> {
	@Query("select max(x.idBoleta) from Boleta x")
	 public abstract List<Integer> listaUltimoRegistro();
	
	@Query("select b from Boleta b,Unidadsolicitante u, Ubigeoorigen o,Ubigeodestino d,Cliente c, Clientedestino p where p.idClientedestino=b.clientedestino.idClientedestino and c.idCliente=b.cliente.idCliente and u.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino and b.botonConsolidacion>0 ORDER BY CAST(b.botonConsolidacion AS int) DESC")
	public abstract List<Boleta> listaParaTablaPrincipal();
	

	@Query("select b from Boleta b,Unidadsolicitante u, Ubigeoorigen o,Ubigeodestino d,Cliente c, Clientedestino p where p.idClientedestino=b.clientedestino.idClientedestino and c.idCliente=b.cliente.idCliente and u.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino  ORDER BY CAST(b.botonConsolidacion AS int) DESC")
	public abstract List<Boleta> listaParaTablaPrincipal2();
	
	@Query("select b from Boleta b,Unidadsolicitante u, Ubigeoorigen o,Ubigeodestino d,Cliente c, Clientedestino p where p.idClientedestino=b.clientedestino.idClientedestino and c.idCliente=b.cliente.idCliente and u.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino and b.idBoleta=?1")
	//@Query("select b from Boleta b where idBoleta=?1")
	public abstract List< Boleta> listaParaTablaPorID(int idBoleta);
	
	//@Query("select a from Boleta a where cmm like :fil")
	@Query("select b from Boleta b,Unidadsolicitante u, Ubigeoorigen o,Ubigeodestino d,Cliente c, Clientedestino p where p.idClientedestino=b.clientedestino.idClientedestino and c.idCliente=b.cliente.idCliente and u.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino and b.cmm like :fil")
	public abstract List<Boleta> buscarPorCmm(@Param("fil") String filtro);
	
	
	//@Query("select a from Boleta a where a.flujo like :var_flujo")
	@Query("select b from Boleta b,Unidadsolicitante u, Ubigeoorigen o,Ubigeodestino d,Cliente c, Clientedestino p where p.idClientedestino=b.clientedestino.idClientedestino and c.idCliente=b.cliente.idCliente and u.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino and b.flujo like :var_flujo")
	public abstract List<Boleta> buscarPorFlujo(@Param("var_flujo")String flujo);
	
	//@Query("select b from Boleta b where b.fechaDestino>=:fecha_desde and b.fechaDestino>=:fecha_hasta")
	//public abstract List<Boleta> listaDentrodeUnRangoFecha(@Param("fecha_desde")Date fechaDesde, @Param("fecha_hasta")Date fechaHasta);
    
	@Query("select b from Boleta b,Unidadsolicitante u, Ubigeoorigen o,Ubigeodestino d,Cliente c, Clientedestino p where p.idClientedestino=b.clientedestino.idClientedestino and c.idCliente=b.cliente.idCliente and u.idUnidadsolicitante=b.unidadsolicitante.idUnidadsolicitante and o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino and b.fechaDestino BETWEEN :fecha_desde and :fecha_hasta and b.botonConsolidacion=4 order by b.fechaDestino ASC" )
	public abstract List<Boleta> listaDentrodeUnRangoFecha(@Param("fecha_desde")Date fechaDesde, @Param("fecha_hasta")Date fechaHasta);
	
	//@Query("select b from Boleta b where  b.usuario.idUsuario =?1")
	//public abstract List<Boleta> lisatboletasUsuario(int idUsuario);
	@Query("select b from Boleta b where b.estadoFirma<>3 and (b.usuario.idUsuario =?1 or b.usuario2.idUsuario=?1)")
	public abstract List<Boleta> lisatboletasUsuario(int idUsuario);
	 @Query("SELECT COALESCE(MAX(b.idBoleta), 0) FROM Boleta b")  
	public abstract Integer findTopByOrderByIdBoletaDesc();
	 @Query("SELECT b FROM Boleta b ORDER BY b.idBoleta DESC")
	 public abstract  List<Boleta> listaBoleta(Pageable pageable);
	 @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Boleta b WHERE b.cmm = :cmm")
	 public abstract boolean existsByCmm(@Param("cmm") String cmm);

	
	@Query("select b from Boleta b where b.estadoFirma = 1 and b.usuario2=1")
	 public abstract List<Boleta> listaboletasFirmadasOrigen();
	 
	// @Query("select b from Boleta b where b.idUsuario2 IS NULL")
	 //public abstract List<Boleta> listaboletasFirmadasOrigen();
		
	 //@Query("select b from Boleta b, Usuario u where b.estadoFirma = 1 and b.usuario2=u.idUsuario and b.usuario2 IS NOT NULL")
		
	 @Query("select b from Boleta b, Usuario u where b.estadoFirma = 1 and b.usuario2=u.idUsuario and b.usuario2 <>1")
	 public abstract List<Boleta> listaboletasRelacionadasConUsuario();
	 
	 @Query("select b from Boleta b where b.estadoFirma = 0 and b.usuario=1")
	 public abstract List<Boleta> listaBolestasSinAsignarEnOrigen();
      
	 @Query("select b from Boleta b where b.estadoFirma = 0  and b.usuario <>1")
	 public abstract List<Boleta> listaboletasAsiganadosOrigen();
	 
	 
	 @Query("select b from Boleta b where b.estadoFirma=?1")
	 public abstract List<Boleta> estadoFirmaIgualDos(Byte estadoFirma);
	
	 @Query("SELECT MAX(b.numguia) FROM Boleta b")
	 public abstract int ultimoNumguia();
	 
	 @Query("SELECT MAX(b.proforma) FROM Boleta b")
	 public abstract int ultimoProforma();

}
