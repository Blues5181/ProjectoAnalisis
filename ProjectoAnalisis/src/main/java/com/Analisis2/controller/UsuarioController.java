package com.Analisis2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Analisis2.entity.Usuario;
import com.Analisis2.repositorio.RepoRol;
import com.Analisis2.servicio.UsuarioServicio;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioServicio usuarioService;
	
	@Autowired
	RepoRol rolRepositorio;
	
	@GetMapping("/")
	public String index() {
		return "index";
				
		
	}
	@GetMapping("/for_usuario")
	public String getformUser(Model model) {
		model.addAttribute("userForm",new Usuario());
		model.addAttribute("userList", usuarioService.getAllUsers());
		model.addAttribute("roles",rolRepositorio.findAll());
		model.addAttribute("listTab","active");
		return "formulario_usuario/vista_usuario";
	}	
}
