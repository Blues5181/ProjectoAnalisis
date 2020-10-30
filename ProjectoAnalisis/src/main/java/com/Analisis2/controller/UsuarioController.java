package com.Analisis2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.stream.Collectors;

import javax.validation.Valid;

import com.Analisis2.dto.ChangePasswordForm;
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
	
@GetMapping("/editUser/{id}")
public String getEditUserForm(Model model, @PathVariable(name="id")Long id)throws Exception
{
	Usuario userToEdit = usuarioService.getUserById(id);
	model.addAttribute("userForm", userToEdit);
	model.addAttribute("userList", usuarioService.getAllUsers());
	model.addAttribute("roles",rolRepositorio.findAll());
	model.addAttribute("formTab","active");	
	model.addAttribute("editMode",true);
	model.addAttribute("passwordForm",new ChangePasswordForm(id));
	
	return "formulario_usuario/vista_usuario";
}

@PostMapping("/editUser")
public String postEditUserForm(@Valid @ModelAttribute("userForm")Usuario usuario,BindingResult result,ModelMap model)
{
	if(result.hasErrors()) {
		model.addAttribute("userForm",usuario);
		model.addAttribute("formTab","active");
		model.addAttribute("editMode",true);
		model.addAttribute("passwordForm",new ChangePasswordForm(usuario.getId()));
		
	}else {
		try {
			usuarioService.updateUser(usuario);
			model.addAttribute("userForm",new Usuario());
			model.addAttribute("listTab","active");
			
		}catch(Exception e) {
			model.addAttribute("formErrorMessage",e.getMessage());
			model.addAttribute("userForm",usuario);
			model.addAttribute("formTab","active");
			model.addAttribute("userList",usuarioService.getAllUsers());
			model.addAttribute("roles",rolRepositorio.findAll());
			model.addAttribute("editMode",true);
		}
	}
	model.addAttribute("userList", usuarioService.getAllUsers());
	model.addAttribute("roles",rolRepositorio.findAll());
		return "formulario_usuario/vista_usuario";
	
	
	}

@GetMapping("/userForm/cancel")
public String cancelEditUser(ModelMap model) {
	return "redirect:/for_usuario";
}

@GetMapping("/deleteUser/{id}")
public String deleteUser(Model model, @PathVariable(name="id") Long id) {
	try {
		usuarioService.deleteUser(id);
	} catch (Exception e) {
		model.addAttribute("listErrorMessage",e.getMessage());
	}
	return getformUser(model);

}

@PostMapping("/editUser/changePassword")
public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, org.springframework.validation.Errors errors)
{
	try {
		if(errors.hasErrors()) {
			
			String result= errors.getAllErrors()
					.stream().map(x-> x.getDefaultMessage())
					.collect(Collectors.joining(""));
			
			throw new Exception(result);
		}
		usuarioService.changePassword(form);
		
	}catch (Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
return ResponseEntity.ok("succes");
}

}