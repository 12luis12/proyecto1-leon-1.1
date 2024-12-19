package com.empresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class EnlaceController {
	//Login
		@GetMapping("/")
		public String verLogin() {	return "login";  }//aui los jsp 
		
		@GetMapping("/verIntranetHome")
		public String verrIntranetHome() {	return "intranetHome";  }
         
		@GetMapping("/verIntranetUbigeo")
		public String ver() {	return "ubigeoJquery";  }
		
		@GetMapping("/vercrudUbigeoorigen")
		public String crudUbigeoorigen() {	return "intranetCrudUbigeoOrigen";  }
		
		
        
        @GetMapping("/vercrudCliente")
         public String crudCliente() {	return "intranetCrudCliente";  }
        
        @GetMapping("/vercrudBoleta")
        public String registroDeBoleta() {	return "registraBoleta";  }
        
        @GetMapping("/vercrudUnidadSlicitante")
        public String registroDeUnidadsolicitante() {	return "intranetCrudUnidadSolicitante";  }
        

        @GetMapping("/verintranetConsolidaciones")
        public String verTablaconsolidaciones() {	return "verConsolidaciones";  }

        @GetMapping("/verCrudUsuario")
        public String crudUsuario() {	return "verCrudUsuario";  }
        
        @GetMapping("/verAsignacionCmmUsuarioDestino")
        public String asignacionCmmUsuarioOrigen() {	return "intranetAsignacionUsuarioDestino";  }
         
        @GetMapping("/verAsignacionUsuarioOrigen")
        public String asignacionCmmUsuarioDestino() {	return "intranetAsignacionUsuarioOrigen";  }
       
        
        @GetMapping("/panelControlGeneral")
        public String verPanelcontrol() {	return "panelControlGeneral";  }
         
        @GetMapping("/crudPrecio")
        public String vercrudPrecio() {	return "verCrudPrecio";  }
        
        @GetMapping("/verConsolidacionConzzartrans")
        public String verConsolidacionConzzatrans() {	return "verConsolidacionConzzartrans";  }
        
        
}


