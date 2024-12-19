package com.empresa.servicio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.empresa.entidad.Boleta;

public interface BoletaServicio {
	public abstract  Boleta insertaBoleta(Boleta obj);
    public abstract List<Integer> listaUltimoRegistro();
    public abstract List<Boleta> buscarPorCmm(String filtro);
    public abstract List<Boleta> listarTodoLasBoletas();
    public abstract List<Boleta> listaParaTablaPrincipal();
    public abstract List<Boleta> listaParaTablaPrincipal2();
    public abstract List<Boleta> listaParaTablaPorID(int idBoleta);
    public abstract List<Boleta> buscarPorFlujo(String flujo);
    public abstract Boleta guardarActualizarBoleta(Boleta boleta);
	public abstract Optional<Boleta> buscarPoID(int id);
	public abstract List<Boleta> listaDentrodeUnRangoFecha(Date fechaDesde, Date fechaHasta);
	
	public abstract List< Boleta> lisatboletasUsuario(int idUsuario);
	public abstract Integer obtenerUltimoRegistro1();
	public abstract List<Boleta> obtener15UltimasBoletas();
	public abstract boolean existeCmm(String cmm);
	
	public abstract List<Boleta> listaboletasRelacionadasConUsuario();
	public abstract List<Boleta> listaboletasFirmadasOrigen();
	
	
	public abstract List<Boleta> listaBolestasSinAsignarEnOrigen();
	public abstract List<Boleta> listaboletasAsiganadosOrigen();
	
	public abstract List<Boleta> estadoFirmaIgualDos(Byte estadoFirma);
	public abstract void actualizarEstadoFirma();
	public abstract int ultimoNumguia();
	public abstract int ultimoProforma();
    
    
}
