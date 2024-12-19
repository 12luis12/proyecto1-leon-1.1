package com.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entidad.Opcion;
import com.empresa.entidad.Rol;
import com.empresa.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	@Query("Select x from Usuario x where x.login = :#{#usu.login} and x.password = :#{#usu.password}")
	public abstract Usuario login(@Param(value = "usu") Usuario bean);
	
	@Query("Select p from Opcion p, RolHasOpcion pr, Rol r, UsuarioHasRol u where "
			+ " p.idOpcion = pr.opcion.idOpcion and "
			+ "pr.rol.idRol = r.idRol and"
			+ " r.idRol = u.rol.idRol and"
			+ " u.usuario.idUsuario = :var_idUsuario")
	public abstract List<Opcion> traerEnlacesDeUsuario(@Param("var_idUsuario") int idUsuario);

	@Query("Select r from Rol r, UsuarioHasRol u where"
			+ " r.idRol = u.rol.idRol and "
			+ "u.usuario.idUsuario = :var_idUsuario")
	public abstract List<Rol> traerRolesDeUsuario(@Param("var_idUsuario")int idUsuario);
	
	public abstract Usuario findByLogin(String login);
	
		
	@Query("Select x from Usuario x where x.login like :var_login")
	public abstract List<Usuario> login2(@Param("var_login") String login);
	
	
}
