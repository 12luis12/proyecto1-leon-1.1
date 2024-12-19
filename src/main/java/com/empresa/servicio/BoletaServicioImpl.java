package com.empresa.servicio;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Boleta;
import com.empresa.repositorio.BoletaRepositorio;
@Service
public class BoletaServicioImpl implements BoletaServicio {
	@Autowired
	private BoletaRepositorio repositorio;

	@Override
	public Boleta insertaBoleta(Boleta obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Integer> listaUltimoRegistro() {
		return repositorio.listaUltimoRegistro();
	}
	@Override
	public Integer obtenerUltimoRegistro1() {
        return repositorio.findTopByOrderByIdBoletaDesc(); // Asegúrate de tener este método en tu repositorio
    }
	@Override
	public List<Boleta> listaParaTablaPrincipal() {
		return repositorio.listaParaTablaPrincipal();
	}
	
	@Override
	public List<Boleta> listaParaTablaPrincipal2() {
		return repositorio.listaParaTablaPrincipal2();
	}

	@Override
	public List<Boleta> buscarPorCmm(String filtro) {
		return repositorio.buscarPorCmm(filtro);
	}

	@Override
	public List<Boleta> listarTodoLasBoletas() {
	
		return repositorio.findAll();
	}

	@Override
	public List<Boleta> listaParaTablaPorID(int idBoleta) {
		return repositorio.listaParaTablaPorID(idBoleta);
	}

	@Override
	public List<Boleta> buscarPorFlujo(String flujo) {
		return repositorio.buscarPorFlujo(flujo);
	}

	@Override
	public Boleta guardarActualizarBoleta(Boleta boleta) {
		return repositorio.save(boleta);
	}
	
	@Override
	public Optional<Boleta> buscarPoID(int id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Boleta> listaDentrodeUnRangoFecha(Date fechaDesde, Date fechaHasta) {
		return repositorio.listaDentrodeUnRangoFecha(fechaDesde, fechaHasta);
	}

	@Override
	public List<Boleta> lisatboletasUsuario(int idUsuario) {
		return repositorio.lisatboletasUsuario(idUsuario);
	}

	@Override
	   public List<Boleta> obtener15UltimasBoletas() {
	        Pageable pageable = PageRequest.of(0, 15, Sort.by(Sort.Direction.DESC, "idBoleta"));
	        return repositorio.listaBoleta(pageable);
	    }
	@Override
	public boolean existeCmm(String cmm) {
	return repositorio.existsByCmm(cmm);
	}

	@Override
	public List<Boleta> listaboletasRelacionadasConUsuario() {
	return repositorio.listaboletasRelacionadasConUsuario();
	}

	@Override
	public List<Boleta> listaboletasFirmadasOrigen() {
	
		return repositorio.listaboletasFirmadasOrigen();
	}

	@Override
	public List<Boleta> listaBolestasSinAsignarEnOrigen() {
	return repositorio.listaBolestasSinAsignarEnOrigen();
	}

	@Override
	public List<Boleta> listaboletasAsiganadosOrigen() {
	return repositorio.listaboletasAsiganadosOrigen();
	}

	@Override
	
	public List<Boleta> estadoFirmaIgualDos(Byte estadoFirma) {
	return repositorio.estadoFirmaIgualDos(estadoFirma);
	}
    
	 public void actualizarEstadoFirma() {
	        List<Boleta> boletas = estadoFirmaIgualDos((byte) 2);

	        for (Boleta boleta : boletas) {
	            boleta.setEstadoFirma((byte) 3);
	        }

	        // Guardar en lote si es soportado por el repositorio
	        repositorio.saveAll(boletas);
	    }

	@Override
	public int ultimoNumguia() {
	return repositorio.ultimoNumguia();
	}

	@Override
	public int ultimoProforma() {
	return repositorio.ultimoProforma();
	}

}
