package com.Analisis2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
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
	
	@PostMapping("/for_usuario")
	public String createUser(@Valid @ModelAttribute("userForm")Usuario usuario,BindingResult result, ModelMap model)
	{if(result.hasErrors()) {
		model.addAttribute("userForm",usuario);
		model.addAttribute("formTab","active");
		
	}else {
		try {
			usuarioService.createUsuario(usuario);
			model.addAttribute("userForm",new Usuario());
			model.addAttribute("listTab","active");
			
		}catch(Exception e) {
			model.addAttribute("formErrorMessage",e.getMessage());
			model.addAttribute("userForm",usuario);
			model.addAttribute("formTab","active");
			model.addAttribute("userList",usuarioService.getAllUsers());
			model.addAttribute("roles",rolRepositorio.findAll());
		}
	}
	model.addAttribute("userList", usuarioService.getAllUsers());
	model.addAttribute("roles",rolRepositorio.findAll());
		return "formulario_usuario/vista_usuario";
		
	}
}
