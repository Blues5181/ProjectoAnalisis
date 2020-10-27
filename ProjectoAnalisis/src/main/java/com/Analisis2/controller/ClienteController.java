package com.Analisis2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
}
