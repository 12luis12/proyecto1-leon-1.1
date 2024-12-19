package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.entidad.Ubigeoorigen;
import com.empresa.servicio.UbigeoorigenServicio;

@Controller
public class UbigeoOrigenCrudController {
	@Autowired
	 private UbigeoorigenServicio servicio;
	
	
	@RequestMapping("/consultaCrudUbigeoorigen")
	public String consulta(String filtro, HttpSession session){
	List<Ubigeoorigen> data=servicio.buscaPorNombre(filtro +"%");
	session.setAttribute("ubigeoorigenes",data);
	return "intranetCrudUbigeoOrigen";
	}
	
	@RequestMapping("/registrarActualizarCrudUbigeoorigen")
	public String resgistrar(Ubigeoorigen obj, HttpSession session){
		try {
			Ubigeoorigen sal=servicio.registraActualizaUbigeoorigen(obj);
			if(sal !=null) {
				session.setAttribute("MENSAJE", "existe error");
			}else {
				session.setAttribute("MENSAJE", "se registro o actualizo correctamente");
			}
			
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Registro exitoso de ubigeo Origen");
			e.printStackTrace();
		}
		
		return "redirect:salida";
	}
	@RequestMapping("/eliminaCrudUbigeoorigen")
	public String elimina(Integer id, HttpSession session){
		try {
			Optional<Ubigeoorigen> obj = servicio.buscarPorId(id);
			if(obj.isPresent()) {
				servicio.eliminaUbigeoorigen(id);
				session.setAttribute("MENSAJE", "Se elimino correctamente");
			}else {
				session.setAttribute("MENSAJE", "No existe el ID");
			}
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Existe Error.....");
			e.printStackTrace();
		}
		return "redirect:salida";
	}
	
	@RequestMapping("/salida")
	public String listarTodos(String filtro, HttpSession session){
	List<Ubigeoorigen> data=servicio.listarTodos();
	session.setAttribute("ubigeoorigenes", data);
	return "intranetCrudUbigeoOrigen";
	}
}
