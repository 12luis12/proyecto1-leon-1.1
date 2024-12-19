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

import com.empresa.entidad.Cliente;
import com.empresa.servicio.ClienteServicio;


@Controller

public class ClienteController {
	@Autowired
	 private ClienteServicio servicio;
	
	@RequestMapping("/consultaCrudCliente")
	public String consulta(String filtro, HttpSession session){
	List<Cliente> data=servicio.buscaClientePorNombre(filtro +"%");
	session.setAttribute("clientes",data);
	return "intranetCrudCliente";
	}
	
	@RequestMapping("/registrarActualizarCrudCliente")
	public String resgistrar(Cliente obj, HttpSession session){
		try {
			Cliente sal=servicio.registraActualizaCliente(obj);
			if(sal ==null) {
				session.setAttribute("MENSAJE", "existe errorrrrr");
			}else {
				session.setAttribute("MENSAJE", "se registro o actualizo correctamente");
			}
			
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Registro exitoso de ubigeo Origen");
			e.printStackTrace();
		}
		
		return "redirect:salidacliente";
	}
	@RequestMapping("/eliminaCrudCliente")
	public String elimina(Integer id, HttpSession session){
		try {
			Optional<Cliente> obj = servicio.buscarClientePorId(id);
			if(obj.isPresent()) {
				servicio.eliminaCliente(id);
				session.setAttribute("MENSAJE", "Se elimino correctamente");
			}else {
				session.setAttribute("MENSAJE", "No existe el ID");
			}
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Existe Error.....");
			e.printStackTrace();
		}
		return "redirect:salidacliente";
	}
	
	@RequestMapping("/salidacliente")
	public String listarTodos(String filtro, HttpSession session){
	List<Cliente> data=servicio.listarTodoLosCliente();
	session.setAttribute("clientes", data);
	return "intranetCrudCliente";
	}
	//___________________________________________
	
	 @GetMapping("/buscar")
	 @ResponseBody
	    public Cliente buscarCliente(@RequestParam String nombre, @RequestParam String apellido) {
	        return servicio.findByNombreAndApellido1(nombre, apellido);
	    }
	    
	    @PostMapping("/guardar")
	    @ResponseBody
	    public Cliente guardarCliente(@RequestBody Cliente cliente) {
	        return servicio.guardarActualizarCliente(cliente);
	    }
	//--------------------------------------------------
	
	
}
