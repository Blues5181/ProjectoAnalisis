package com.Analisis2.servicio;

import java.util.Optional;

import com.Analisis2.Exception.UsernameOrIdNotFound;
import com.Analisis2.entity.Producto;

public interface ProductoServicio {

public Iterable<Producto> getAllProducto();

	
	public Optional<Producto> findByNombre(String nombre);
	public Producto getProductoById(Long id) throws Exception;
	public Producto createProducto(Producto producto) throws Exception;
	public Producto updateProducto(Producto producto) throws Exception;

	public void deleteProducto(Long id) throws UsernameOrIdNotFound;
	
	
	
}
