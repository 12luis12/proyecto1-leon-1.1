package com.empresa.servicio;

import java.util.List;

import com.empresa.entidad.Ubigeodestino;



public interface UbigeodestinoServicio {
	public abstract List<String> listaDepartamentos1();
	public abstract List<String> listaProvincias1(String departamento1);
	public abstract List<String> listaMunicipios1(String departamento1, String provincia1);
	public abstract List<Ubigeodestino> listaAlmacenes1(String departamento1, String provincia1, String municipio1);
	
	
	
	public abstract List< Ubigeodestino> listaParaUbigeodestino(int idBoleta);

}
