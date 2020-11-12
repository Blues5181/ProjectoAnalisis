package com.Analisis2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Analisis2.Exception.UsernameOrIdNotFound;

import com.Analisis2.entity.Cliente;

import com.Analisis2.servicio.ClienteServicio;



@Controller
public class ClienteController {

	
@Autowired
ClienteServicio clienteService;


@GetMapping("/for_client")
public String getformClient(Model model) {
	model.addAttribute("clientForm",new Cliente());
	model.addAttribute("clientList", clienteService.getAllClients());
	model.addAttribute("listTab","active");
	return "formulario_cliente/vista_client";
}	


@PostMapping("/for_client")
public String createClient(@Valid @ModelAttribute("clientForm")Cliente cliente,BindingResult result,ModelMap model)
{if(result.hasErrors()) {
	
	model.addAttribute("clientForm",cliente);
	model.addAttribute("formTab","active");
	
}else {
	try {
		
		clienteService.createCliente(cliente);
		model.addAttribute("clientForm",new Cliente());
		model.addAttribute("listTab","active");
	}catch(Exception e) {
		
		model.addAttribute("formErrorMessage",e.getMessage());
		model.addAttribute("clientForm",cliente);
		model.addAttribute("formTab","active");
		model.addAttribute("clientList",clienteService.getAllClients());
		
	}
	
}
model.addAttribute("clientList",clienteService.getAllClients());
return "formulario_cliente/vista_client";

}

@GetMapping("/editClient/{id}")
public String getEditClientForm(Model model, @PathVariable(name="id")Long id)throws Exception
{
	Cliente clientToEdit = clienteService.getClienteById(id);
	model.addAttribute("clientForm", clientToEdit);
	model.addAttribute("clientList", clienteService.getAllClients());
	model.addAttribute("formTab","active");	
	model.addAttribute("editMode",true);

	
	return "formulario_cliente/vista_client";
}
@PostMapping("/editClient")
public String postEditUserForm(@Valid @ModelAttribute("clientForm")Cliente cliente,BindingResult result,ModelMap model)
{
	if(result.hasErrors()) {
		model.addAttribute("clientForm",cliente);
		model.addAttribute("formTab","active");
		model.addAttribute("editMode",true);
	
		
	}else {
		try {
			clienteService.updateCliente(cliente);
			model.addAttribute("clientForm",new Cliente());
			model.addAttribute("listTab","active");
			
		}catch(Exception e) {
			model.addAttribute("formErrorMessage",e.getMessage());
			model.addAttribute("clientForm",cliente);
			model.addAttribute("formTab","active");
			model.addAttribute("clientList",clienteService.getAllClients());
			model.addAttribute("editMode",true);
		}
	}
	model.addAttribute("clientList", clienteService.getAllClients());
		return "formulario_cliente/vista_client";
	
	
	}

@GetMapping("/clientForm/cancel")
public String cancelEditClient(ModelMap model) {
	return "redirect:/for_client";
}

@GetMapping("/deleteClient/{id}")
public String deleteCliente(Model model, @PathVariable(name="id") Long id) {
	try {
		clienteService.deleteCliente(id);
	} catch (UsernameOrIdNotFound uoin) {
		model.addAttribute("listErrorMessage",uoin.getMessage());
	}
	return getformClient(model);

	
}


}
