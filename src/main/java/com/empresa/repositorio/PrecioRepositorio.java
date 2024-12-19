package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entidad.Boleta;
import com.empresa.entidad.Precio;

public interface PrecioRepositorio extends JpaRepository<Precio, Integer>{
	// @Query(value="select p.normalMinima from Boleta b,Precio p, Ubigeoorigen o, Ubigeodestino d where ((o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen) and (o.idUbigeoorigen=p.ubigeoorigen.idUbigeoorigen))and ((d.idUbigeodestino=b.ubigeodestino.idUbigeodestino) and (d.idUbigeodestino=p.ubigeodestino.idUbigeodestino)) and b.idBoleta=?1",nativeQuery = true)
		
	 //@Query(value="select p.normalMinima from Boleta b,Precio p, Ubigeoorigen o, Ubigeodestino d where o.idUbigeoorigen=b.idUbigeoorigen=p.idUbigeoorigen and d.idUbigeodestino=b.idUbigeodestino=p.idUbigeodestino and b.idBoleta=?1",nativeQuery = true)
	@Query(value="select p.* from boleta b,precio p where b.idUbigeoorigen=p.idUbigeoorigen and b.idUbigeodestino=p.idUbigeodestino and b.idBoleta=?1",nativeQuery = true)
	 public abstract List< Precio> listaParaPrecio(int idBoleta);
	
	

	@Query("select case when count(e) > 0 then true else false end from Precio e where e.ubigeoorigen.idUbigeoorigen = ?1 and e.ubigeodestino.idUbigeodestino = ?2")
	boolean existeIdUbigeoorigenIdUbigeodestino(int idUbigeoorigen, int idUbigeodestino);

	
	@Query("select b from Precio b, Ubigeoorigen o,Ubigeodestino d where  o.idUbigeoorigen=b.ubigeoorigen.idUbigeoorigen and  d.idUbigeodestino=b.ubigeodestino.idUbigeodestino")
	public abstract List<Precio> listaPrecios();
	
}
