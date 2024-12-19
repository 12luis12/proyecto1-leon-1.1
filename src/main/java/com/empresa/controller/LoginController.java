package com.empresa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.entidad.Opcion;
import com.empresa.entidad.Rol;
import com.empresa.entidad.Usuario;
import com.empresa.servicio.UsuarioServicio;

@Controller
public class LoginController {
	@Autowired
	private UsuarioServicio servicio;

	@RequestMapping("/login")
	public String login(Usuario usu, HttpSession session, HttpServletRequest request) {
		Usuario objUsu= servicio.login(usu);
		if(objUsu==null)
		{
			 request.setAttribute("mensaje", "el usuario no existe");
			return "login";
		}else {
			List<Rol> roles=servicio.traerRolesDeUsuario(objUsu.getIdUsuario());
			List<Opcion> menus=servicio.traerEnlacesDeUsuario(objUsu.getIdUsuario());
			session.setAttribute("objUsuario", objUsu);
			session.setAttribute("objRoles", roles);
			session.setAttribute("objMenus", menus);
			return"redirect:home";
		}
		
		
	}
	@RequestMapping("/home")
	public String salida() {
		return"intranetHome";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();

		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-cache");

		request.setAttribute("mensaje", "El usuario salió de sesión");
		return "login";

	}
}
