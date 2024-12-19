package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entidad.Opcion;
import com.empresa.entidad.Rol;
import com.empresa.entidad.Usuario;
import com.empresa.servicio.UsuarioServicio;
import com.empresa.util.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login2(@RequestBody Usuario usuario, HttpSession session, HttpServletRequest request) {
	    List<Usuario> objUsu = usuarioServicio.login2(usuario.getLogin());

	    if (objUsu.isEmpty()) {
	        // Usuario no encontrado
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectaaaaaaaaaaaa");
	    } 
	    
	    String passwordHashed = objUsu.get(0).getPassword();
	    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	    
	    // Verificar la contraseña
	    if (argon2.verify(passwordHashed, usuario.getPassword())) {
	    	
	    	String tokenJWT=jwtUtil.create(String.valueOf(objUsu.get(0).getIdUsuario()), objUsu.get(0).getLogin());
	    	
	        // Autenticación exitosa
	        List<Rol> roles = usuarioServicio.traerRolesDeUsuario(objUsu.get(0).getIdUsuario());
	        List<Opcion> menus = usuarioServicio.traerEnlacesDeUsuario(objUsu.get(0).getIdUsuario());

	        // Guardar en sesión
	        session.setAttribute("objUsuario", objUsu.get(0));
	        session.setAttribute("objRoles", roles);
	        session.setAttribute("objMenus", menus);

	        // Construir la respuesta
	        Map<String, Object> response = new HashMap<>();
	        response.put("usuario", objUsu.get(0));
	        response.put("roles", roles);
	        response.put("menus", menus);
	        response.put("tokenJWT", tokenJWT);

	        return ResponseEntity.ok(response);
	        
	    } else {
	        // Contraseña incorrecta
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrecta");
	    }
	    
	    
	  	}
	
	
	    @RequestMapping("/home")
			public String salida() {
				return"intranetHome";
			}
}
