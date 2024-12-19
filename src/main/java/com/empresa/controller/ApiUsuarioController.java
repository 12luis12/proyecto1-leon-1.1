package com.empresa.controller;




import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entidad.Usuario;

import com.empresa.servicio.UsuarioServicio;
import com.empresa.util.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/usuarios")
public class ApiUsuarioController {
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/listaUsuarios")
	public ResponseEntity<?> listaUsuario(@RequestHeader(value = "Authorization") String token) {
	    log.info("Método --> listaUsuario");

	    // Validar el token
	    ResponseEntity<String> tokenValidationResponse = validaToken(token);
	    if (tokenValidationResponse != null) {
	        return tokenValidationResponse; // Devuelve el error si el token no es válido
	    }

	    // Si el token es válido, devolver la lista de usuarios
	    try {
	        List<Usuario> usuarios = usuarioServicio.listaUsuario();
	        return ResponseEntity.ok(usuarios);
	    } catch (DataAccessException e) {
	        // Captura excepciones relacionadas con la base de datos
	        log.error("Error de acceso a datos al recuperar usuarios", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error de acceso a la base de datos al recuperar usuarios.");
	    } catch (Exception e) {
	        // Captura cualquier otra excepción que no haya sido manejada anteriormente
	        log.error("Error inesperado al recuperar usuarios", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error inesperado en el servidor.");
	    }
	}




	

	@PostMapping("/registrarUsuarios")
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario obj) {
		log.info("METODO --> registrar");
		Argon2 argon2=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash= argon2.hash(2, 1024, 2,obj.getPassword());
		obj.setPassword(hash);
		return ResponseEntity.ok(usuarioServicio.guardaUsuario(obj));
	}
	
	@PutMapping("/actualizaUsuario")
	public ResponseEntity<Usuario> actualizaUsuario(@RequestBody Usuario usuario) {
	    log.info("Método --> actualiza " + usuario.getIdUsuario());
	    
	    Optional<Usuario> obj = usuarioServicio.buscarUsuarioPorId(usuario.getIdUsuario());
	    if (obj.isPresent()) {
	        Usuario usuarioExistente = obj.get();

	        // Verificar si se ha proporcionado una nueva contraseña
	        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
	            // Encriptar la nueva contraseña
	            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	            String hashedPassword = argon2.hash(4, 1024 * 1024, 8, usuario.getPassword());
	            usuario.setPassword(hashedPassword);
	        } else {
	            // Mantener la contraseña actual si no se ha proporcionado una nueva
	            usuario.setPassword(usuarioExistente.getPassword());
	        }

	        // Actualizar los datos del usuario, incluida la contraseña (si ha cambiado)
	        Usuario usuarioActualizado = usuarioServicio.insertaActualizaUsuario(usuario);

	        return ResponseEntity.ok(usuarioActualizado);
	    } else {
	        log.error("Id " + usuario.getIdUsuario() + " no existe");
	        return ResponseEntity.notFound().build();
	    }
	}

	
	
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> elimina(@RequestHeader(value="Authorization") String token,@PathVariable int id) {
		log.info("METODO --> elimina " + id);
		
		 ResponseEntity<String> tokenValidationResponse = validaToken(token);
		    if (tokenValidationResponse != null) {
		        return tokenValidationResponse; // Devuelve el error si el token no es válido
		    }
		
		    try {
		    	Optional<Usuario> obj = usuarioServicio.buscarUsuarioPorId(id);
				if (obj.isPresent()) {
					usuarioServicio.eliminarUsuario(id);
					return ResponseEntity.ok(obj.get());
				} else {
					log.error("Id " + id + " no existe");
					return ResponseEntity.notFound().build();
				}
		    } catch (Exception e) {
		        log.error("Error al recuperar usuarios", e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
		    }
			
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable int id) {
		log.info("METODO --> buscarPorId " + id);
		Optional<Usuario> obj = usuarioServicio.buscarUsuarioPorId(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok(obj.get());
		} else {
			log.error("Id " + id + " no existe");
			return ResponseEntity.notFound().build();
		}
	}
	
	private ResponseEntity<String> validaToken(String authHeader) {
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado o formato incorrecto");
	    }

	    String token = authHeader.substring(7); // Eliminar 'Bearer ' del encabezado

	    try {
	        String idUsuario = jwtUtil.getKey(token);
	        if (idUsuario == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
	        }
	        return null; // Token válido, continúa con el procesamiento
	    } catch (Exception e) {
	        log.error("Error al procesar el token", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}

	
	
}
