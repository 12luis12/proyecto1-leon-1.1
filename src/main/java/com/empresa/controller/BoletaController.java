package com.empresa.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entidad.Boleta;
import com.empresa.entidad.Cliente;
import com.empresa.entidad.Clientedestino;
import com.empresa.entidad.Material;
import com.empresa.entidad.Unidadsolicitante;
import com.empresa.servicio.BoletaServicio;
import com.empresa.servicio.ClienteServicio;
import com.empresa.servicio.ClientedestinoServicio;
import com.empresa.servicio.MaterialServicio;
import com.empresa.servicio.UnidadsolicitanteServicio;

@Controller

public class BoletaController {
	@Autowired
	private BoletaServicio boletaServicio;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ClientedestinoServicio clientedestinoServicio;
	@Autowired
	private MaterialServicio materialServicio;
	@Autowired
	private UnidadsolicitanteServicio unidadsolicitanteServicio;
	
	@RequestMapping("/verBoleta")
	public String ver() {
		return "registraBoleta";
	}
	
	@RequestMapping("/consultarBoletaUltimoRegistro")
	@ResponseBody
	public List<Integer> listaultimo() {
		return boletaServicio.listaUltimoRegistro();
	}
	
	//para ver material-----------------------------------------
	@RequestMapping("/consultarMaterialEstado")
	public String consulta(int filtro, HttpSession session){
	List<Material> data=materialServicio.buscarPorEstado(filtro);
	session.setAttribute("materiales",data);
	
	return "redirect:salidaboleta";
	}
	//----------------------------------------------------------
	
	
	@GetMapping("/copiaMaterialesAboleta")
	@ResponseBody
	public List<Boleta> copiaDescripcionMaterialaBoleta(@RequestParam int idBoleta) {
	    // Obtener las boletas y materiales asociados
	    List<Boleta> boletas = boletaServicio.listaParaTablaPorID(idBoleta);
	    List<Material> materiales = materialServicio.listaParaMaterial(idBoleta);

	    // Manejar el caso donde no se encuentran datos
	    if (boletas.isEmpty() || materiales.isEmpty()) {
	        throw new RuntimeException("Datos no encontrados para idBoleta: " + idBoleta);
	    }

	    try {
	        // Obtener la primera boleta de la lista
	        Boleta boleta = boletas.get(0);

	        // Obtener el acumulador actual de la boleta
	        String acumuladorEnBoleta = boleta.getAcumulador();
	        if (acumuladorEnBoleta == null) {
	            acumuladorEnBoleta = "";
	        }

	        // Construir la descripción de los cambios detectados
	        StringBuilder cambiosDescripcionMaterial = new StringBuilder();

	        // Recorre los materiales para identificar y acumular los cambios
	        for (Material material : materiales) {
	            // Obtener los campos actuales del material
	            String descripcionActual = material.getDescripcion();
	            String kilogramoActual = material.getPesoKg();
	            BigDecimal cantidadActual = material.getCantidad();
	            String volumenActual = material.getVolumenM3();
	            byte cobradoporActual = material.getCobrarpor();
	            String valordecobrarporActual = (cobradoporActual == 0) ? "Cobrado:Kg" : "Cobrado:M3";
	            String observacionesActual = material.getObservaciones();

	            // Solo agregar campos modificados que no estén ya registrados en el acumulador
	            if (!acumuladorEnBoleta.contains(descripcionActual)) {
	                cambiosDescripcionMaterial.append("Descripción modificada: ").append(descripcionActual).append("\n");
	            }
	            if (!acumuladorEnBoleta.contains(kilogramoActual)) {
	                cambiosDescripcionMaterial.append("Peso modificado: ").append(kilogramoActual).append("\n");
	            }
	            if (!acumuladorEnBoleta.contains(cantidadActual.toString())) {
	                cambiosDescripcionMaterial.append("Cantidad modificada: ").append(cantidadActual).append("\n");
	            }
	            if (!acumuladorEnBoleta.contains(volumenActual)) {
	                cambiosDescripcionMaterial.append("Volumen modificado: ").append(volumenActual).append("\n");
	            }
	            if (!acumuladorEnBoleta.contains(valordecobrarporActual)) {
	                cambiosDescripcionMaterial.append("Valor a Cobrar modificado: ").append(valordecobrarporActual).append("\n");
	            }
	            if (!acumuladorEnBoleta.contains(observacionesActual)) {
	                cambiosDescripcionMaterial.append("Observaciones modificadas: ").append(observacionesActual).append("\n");
	            }
	        }

	        // Eliminar el último salto de línea si es necesario
	        if (cambiosDescripcionMaterial.length() > 0) {
	            cambiosDescripcionMaterial.setLength(cambiosDescripcionMaterial.length() - 1); // Eliminar el último salto de línea
	        }

	        // Solo actualizar si hay cambios detectados
	        if (cambiosDescripcionMaterial.length() > 0) {
	            String resultadoCambios = cambiosDescripcionMaterial.toString();

	            // Agregar los nuevos cambios al acumulador sin alterar lo existente
	            boleta.setAcumulador(acumuladorEnBoleta + (acumuladorEnBoleta.isEmpty() ? "" : "\n*") + resultadoCambios);

	            // Actualizar la descripción con el resumen de los materiales concatenados (solo las descripciones)
	            StringBuilder solodescripcionmaterial = new StringBuilder();
	            for (Material material : materiales) {
	                solodescripcionmaterial.append(material.getDescripcion()).append(". ");
	            }
	            if (solodescripcionmaterial.length() > 0) {
	                solodescripcionmaterial.setLength(solodescripcionmaterial.length() - 1); // Eliminar el último punto
	            }
	            boleta.setDescripcion(solodescripcionmaterial.toString());

	            // Guardar la boleta actualizada en la base de datos
	            boletaServicio.guardarActualizarBoleta(boleta);
	        } else {
	            System.out.println("No se encontraron cambios en los materiales.");
	        }

	        // Retornar las boletas
	        return boletas;

	    } catch (Exception e) {
	        // Manejo de excepción
	        e.printStackTrace(); // Opcional: imprimir la excepción para debug
	        throw new RuntimeException("Error al copiar materiales a boleta: " + e.getMessage());
	    }
	}




	  @RequestMapping("/actualizaCampoEstadoMaterial")
	  public String listmaterial( HttpSession session) {
		  try {
			  List<Material> sal=materialServicio.listaMaterialEstadoCero();
			  for(Material estado:sal) {
					estado.setEstado(1);
				  //Boolean este=true;
					 //estado.setEstado(true);
					 //Boolean.parseBoolean(estado)
				 }
			  Iterable<Material>  gua= materialServicio.registrarTodosLosCeros(sal);
			
			  if(gua!=null ) { 
				  session.setAttribute("MENSAJE", "se registro con exitoooo ");
			  }else {
				  session.setAttribute("MENSAJE", "error al registrarseeeee");
				  
			  }
			  
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "error al registrar");
			e.printStackTrace();
		}
		 return "redirect:salidaboleta";
		  
	  }
	  //______________actualiza estado Boleta----------
	  
	
	 
	  @RequestMapping("/actualizaCampoEstadoMaterial1")
	    public ResponseEntity<String> actualizarEstadoMateriales() {
	        List<Material> materiales = materialServicio.listaMaterialEstadoCero();
	        for (Material material : materiales) {
	            material.setEstado(1);
	        }
	        materialServicio.registrarTodosLosCeros(materiales);
	        return ResponseEntity.ok("success");
	    }
	//----------------------------------------------------
	@RequestMapping("/registraMaterial")
	public String resgistrar(Material obj, HttpSession session){
		try {
			Material sal=materialServicio.registraMaterial(obj);
			if(sal !=null) {
				session.setAttribute("MENSAJE", "se registro o actualizo correctamente");
			}else {
				session.setAttribute("MENSAJE", "Error al registrar");
			}
			
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Registro exitoso de Material");
			e.printStackTrace();
		}
		
		return "redirect:salidaboleta";
	}
	//-----------------registrar Campo acumulador ---------------------------------
	 @PostMapping("/registrarCampoAcumulador")
	 public void guardarCopiaEnAcumulador(@RequestBody Material material) {
	        materialServicio.guardarCopiaEnAcumulador(material);
	    }
	
	//--------------------------------------------
	
	@RequestMapping("/cargaCliente")
	@ResponseBody
	public List<Cliente> listaCliente() {
		return clienteServicio.listarTodoLosCliente();
	}
	
	@RequestMapping("/cargaClientedestino")
	@ResponseBody
	public List<Clientedestino> listaCliente1() {
		return clientedestinoServicio.listarTodoLosClientedestino();
	}
	
	@RequestMapping("/cargaUnidadsolicitante")
	@ResponseBody
	public List<Unidadsolicitante> listaUnidadsolicitante() {
		return unidadsolicitanteServicio.listarTodoLosUnidadsolicitante();
	}
	
	@RequestMapping("/registrarActualizarCrudBoleta")
	public String regBoleta(Boleta obj, HttpSession session) {
		try {
			
			Boleta objSalida=boletaServicio.insertaBoleta(obj);
			if(objSalida!=null)
			{
				session.setAttribute("MENSAJE", "se registro correctamente");
			}else {
				
				session.setAttribute("MENSAJE", "error al registrar");
			}
			
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "error al registrar");
			e.printStackTrace();
		}
		 
		return "redirect:salidaboleta";
	}
	@PostMapping("/registraYactualizaBoleta")
	public ResponseEntity<Boleta> registrarBoletaYActualiza(@RequestBody Boleta obj) {
		return ResponseEntity.ok(boletaServicio.insertaBoleta(obj));
	}
	//guardaboleta
    @RequestMapping("/guardarBoleta")
	 	public Boleta guardarBoleta(Boleta obj) {
    	
  	    return boletaServicio.guardarActualizarBoleta(obj);
    }
	
	
	
	
	@RequestMapping("/registrarActualizarCrudCliente1")
	@ResponseBody
	public String resgistrar(Cliente obj, HttpSession session){
		try {
			Cliente sal=clienteServicio.registraActualizaCliente(obj);
			if(sal ==null) {
				session.setAttribute("MENSAJE", "existe errorrrrr");
			}else {
				session.setAttribute("MENSAJE", "se registro o actualizo correctamente");
			}
			
		} catch (Exception e) {
			session.setAttribute("MENSAJE", "Registro exitoso de ubigeo Origen");
			e.printStackTrace();
		}
		
		return "redirect:salidaboleta";
	}
	//unidad solicitante registra 
	@RequestMapping("/registrarActualizarCrudUnidadSolicitante1")
	@ResponseBody
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
		
		return "redirect:salidaboleta";
	
	}
	
	  //esto es para buscar el flujo
	  

	@GetMapping("/buscarFlujo")
    public ResponseEntity<List<Boleta>> buscarFlujo(@RequestParam String flujo) {
    List<Boleta> boletas = boletaServicio.buscarPorFlujo(flujo);
     return new ResponseEntity<>(boletas, HttpStatus.OK);
    }
	//@RequestMapping("/salidahome")
	//public String salida() {
   		//return "intranetHome";
	//}
	
	@RequestMapping("/salidaboleta")
	public String listarMateriales(String filtro, HttpSession session){
	List<Material> data=materialServicio.buscarPorEstado(0);
	session.setAttribute("materiales", data);
	return "registraBoleta";

	}
	
	
	
}
