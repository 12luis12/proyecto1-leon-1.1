package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Unidadsolicitante;
import com.empresa.servicio.UnidadsolicitanteServicio;


@Controller
public class UnidadsolicitanteController {
	@Autowired
	 private UnidadsolicitanteServicio unidadsolicitanteServicio;
	
	@RequestMapping("/consultaCrudUnidadsolicitante")
	public String consulta(String filtro, HttpSession session){
	List<Unidadsolicitante> data=unidadsolicitanteServicio.buscarPorNombreUnidadsolicitante(filtro +"%");
	session.setAttribute("unidadsolicitantes",data);
	return "intranetCrudUnidadSolicitante";
	}
	
	@RequestMapping("/registrarActualizarCrudUnidadSolicitante")
	public String resgistrar(Unidadsolicitante obj, HttpSession session){
		try {
			Unidadsolicitante sal=unidadsolicitanteServicio.registraActualizaUnidadSolicitante(obj);
			if(sal ==null) {
				session.setAttribute("MENSAJE", "existe errorrrrr");
			}else {
				session.setAttribute("MENSAJE", "se registro o actualizo correctamente");
			}
			
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Registro exitoso de ubigeo Origen");
			e.printStackTrace();
		}
		
		return "redirect:salidaUnidadsolicitante";
	}
	@RequestMapping("/eliminaCrudUnidadsolicitante")
	public String elimina(Integer id, HttpSession session){
		try {
			Optional<Unidadsolicitante> obj = unidadsolicitanteServicio.buscarUnidadsolicitantePorId(id);
			if(obj.isPresent()) {
				unidadsolicitanteServicio.eliminaUnidadsolicitante(id);
				session.setAttribute("MENSAJE", "Se elimino correctamente");
			}else {
				session.setAttribute("MENSAJE", "No existe el ID");
			}
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Existe Error.....");
			e.printStackTrace();
		}
		return "redirect:salidaUnidadsolicitante";
	}
	
	@RequestMapping("/salidaUnidadsolicitante")
	public String listarTodos(String filtro, HttpSession session){
	List<Unidadsolicitante> data=unidadsolicitanteServicio.listarTodoLosUnidadsolicitante();
	session.setAttribute("unidadsolicitantes", data);
	return "intranetCrudUnidadSolicitante";
	}
	//_____________________________________________________________
	 @GetMapping("/buscarUnidadsolicitante")
	 @ResponseBody
	    public Unidadsolicitante buscarUnidadsolicitante(@RequestParam String nombre, @RequestParam String apellido) {
	        return unidadsolicitanteServicio.findByNombreAndApellidounidadS(nombre, apellido);
	    }
	 
	 @PostMapping("/guardarUnidadsolicitante")
	 @ResponseBody
	    public Unidadsolicitante guardarUnidadsolictante(@RequestBody Unidadsolicitante unidadsolicitante) {
	        return unidadsolicitanteServicio.guardarActualizarUnidadsolicitante(unidadsolicitante);
	    }
	 

	//-----------------------------------------------------------
	
}
