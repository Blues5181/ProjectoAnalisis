package com.Analisis2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

	@GetMapping("/")
	public String index() {
		return "index";
				
		
	}
	@GetMapping("/for_usuario")
	public String getformUser() {
		return "formulario_usuario/vista_usuario";
	}	
}
