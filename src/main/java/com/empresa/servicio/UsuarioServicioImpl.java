package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.empresa.entidad.Opcion;
import com.empresa.entidad.Rol;
import com.empresa.entidad.Usuario;
import com.empresa.repositorio.UsuarioRepositorio;
@Service
public class UsuarioServicioImpl implements UsuarioServicio{
   
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	@Override
	public Usuario login(Usuario bean) {
		return usuarioRepository.login(bean);
	}
	@Override
	public List<Usuario>login2(String login) {
		return usuarioRepository.login2(login);
	}
	@Override
	public List<Opcion> traerEnlacesDeUsuario(int idUsuario) {
		return usuarioRepository.traerEnlacesDeUsuario(idUsuario);
	}

	@Override
	public List<Rol> traerRolesDeUsuario(int idUsuario) {
		return usuarioRepository.traerRolesDeUsuario(idUsuario);
	}

	@Override
	public Usuario buscaPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	@Override
	public List<Usuario> listaUsuario() {
		return usuarioRepository.findAll();
	}
   
	@Override
	public Usuario guardaUsuario(Usuario obj) {
		return usuarioRepository.save(obj);
	}
	@Override
	public Optional<Usuario> buscarUsuarioPorId(int id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario insertaActualizaUsuario(Usuario obj) {
		return usuarioRepository.save(obj);
	}
	
	
	@Override
	public void eliminarUsuario(int idUsaurio) {
		usuarioRepository.deleteById(idUsaurio);
	}

}
