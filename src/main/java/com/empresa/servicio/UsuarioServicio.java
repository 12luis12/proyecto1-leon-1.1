package com.empresa.servicio;

import java.util.List;
import java.util.Optional;


import com.empresa.entidad.Opcion;
import com.empresa.entidad.Rol;
import com.empresa.entidad.Usuario;

public interface UsuarioServicio {
	public abstract Usuario login(Usuario bean);
	public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario);
	public abstract List<Rol> traerRolesDeUsuario(int idUsuario);
	public abstract Usuario buscaPorLogin(String login);
	
	public abstract List<Usuario> listaUsuario();
	//public abstract List<Pasatiempo> traerPasatiempoDeUsuario(int idUsuario);
	
	public abstract Usuario guardaUsuario(Usuario obj);
	public Optional<Usuario> buscarUsuarioPorId(int id);
	public Usuario insertaActualizaUsuario(Usuario obj);
	public void eliminarUsuario(int idUsaurio);
	public abstract List<Usuario> login2(String login);
	
	
	
	}
