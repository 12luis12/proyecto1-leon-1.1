package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Material;
import com.empresa.entidad.Precio;
import com.empresa.repositorio.MaterialRepositorio;

@Service
public class MaterialServicioImpl implements MaterialServicio {
	@Autowired
	private MaterialRepositorio repositorio;

	@Override
	public List<Material> buscarPorEstado(int filtro) {
		return repositorio.buscarPorEstado(filtro);
	}

	@Override
	public Material registraMaterial(Material obj) {
		return repositorio.save(obj);
		
	}

	@Override
	public void eliminaMaterial(int id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public List<Material> listaMaterialEstadoCero() {
		return repositorio.listaMaterialEstadoCero();
	}

	@Override
	public Iterable<Material> registrarTodosLosCeros(Iterable<Material> myList) {
		return repositorio.saveAll(myList);
	}

	@Override
	public List<Material> listaParaMaterial(int idBoleta) {
		return repositorio.listaParaMaterial(idBoleta);
	}

	@Override
	public Material listaMaterialPorId(int idMaterial) {

		return repositorio.listaMaterialPorId(idMaterial);
	}
	@Override
	public Material guardarActualizarMaterial(Material material) {
		return repositorio.save(material);
	}
	@Override
    public Material findById(int id) {
        return repositorio.findById(id).orElse(null);
    }
	
		
	@Override
	public Optional<Material> buscarMaterialPorId(int id) {
	return repositorio.findById(id);
	}
	

	//@Override
	//public Material registrarTodosLosCeros(List<Material> myList) {
		//return (Material) repositorio.saveAll(myList);
//	}

	

  

  //@Async
  @Transactional
  @Override
  public void guardarCopiaEnAcumulador(Material material) {
      String copia = material.getObservaciones() + " " + material.getDescripcion() ;
      String acumuladorAnterior = material.getAcumulador() != null ? material.getAcumulador() : "";
         material.setAcumulador(acumuladorAnterior + copia + ";");
         System.out.println("Guardando material: " + material);
         System.out.println("Acumulador antes de guardar: " + material.getAcumulador());
      repositorio.save(material);
      System.out.println("Material guardado: " + material);
  }


	

	

	
	

}
