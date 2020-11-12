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
import com.Analisis2.entity.Producto;
import com.Analisis2.servicio.ProductoServicio1;


@Controller
public class ProductoController {
	
@Autowired
ProductoServicio1 productoService;

	@GetMapping("/for_Producto")
	public String getformProducto(Model model) {
		model.addAttribute("productoForm",new Producto());
		model.addAttribute("productoList", productoService.getAllProducto());
		model.addAttribute("listTab","active");
		return "formulario_producto/vista_producto";
	}	


	@PostMapping("/for_Producto")
	public String createProducto(@Valid @ModelAttribute("productoForm")Producto producto,BindingResult result,ModelMap model)
	{if(result.hasErrors()) {
		
		model.addAttribute("productoForm",producto);
		model.addAttribute("formTab","active");
		
	}else {
		try {
			
			productoService.createProducto(producto);
			model.addAttribute("productoForm",new Producto());
			model.addAttribute("listTab","active");
		}catch(Exception e) {
			
			model.addAttribute("formErrorMessage",e.getMessage());
			model.addAttribute("productoForm",producto);
			model.addAttribute("formTab","active");
			model.addAttribute("productoList",productoService.getAllProducto());
			
		}
		
	}
	model.addAttribute("productoList",productoService.getAllProducto());
	return "formulario_producto/vista_producto";

	}

	@GetMapping("/editProducto/{id}")
	public String getEditProductoForm(Model model, @PathVariable(name="id")Long id)throws Exception
	{
		Producto productoToEdit = productoService.getProductoById(id);
		model.addAttribute("productoForm", productoToEdit);
		model.addAttribute("productoList", productoService.getAllProducto());
		model.addAttribute("formTab","active");	
		model.addAttribute("editMode",true);

		
		return "formulario_producto/vista_producto";
	}
	@PostMapping("/editProducto")
	public String postEditProductoForm(@Valid @ModelAttribute("productoForm")Producto producto,BindingResult result,ModelMap model)
	{
		if(result.hasErrors()) {
			model.addAttribute("productoForm",producto);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode",true);
		
			
		}else {
			try {
				productoService.updateProducto(producto);
				model.addAttribute("productoForm",new Producto());
				model.addAttribute("listTab","active");
				
			}catch(Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("productoForm",producto);
				model.addAttribute("formTab","active");
				model.addAttribute("productoList",productoService.getAllProducto());
				model.addAttribute("editMode",true);
			}
		}
		model.addAttribute("productoList", productoService.getAllProducto());
			return "formulario_producto/vista_producto";
		
		
		}

	@GetMapping("/productoForm/cancel")
	public String cancelEditProducto(ModelMap model) {
		return "redirect:/for_Producto";
	}

	@GetMapping("/deleteProducto/{id}")
	public String deleteProducto(Model model, @PathVariable(name="id") Long id) {
		try {
			productoService.deleteProducto(id);
		} catch (UsernameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage",uoin.getMessage());
		}
		return getformProducto(model);

		
	
	
	
}
}
