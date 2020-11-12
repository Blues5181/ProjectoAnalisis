package com.Analisis2.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.Analisis2.Exception.UsernameOrIdNotFound;
import com.Analisis2.entity.Producto;
import com.Analisis2.repositorio.ProductoRepo;

@Service
public class ProductoSerivicoImp implements ProductoServicio1 {

	@Autowired
	ProductoRepo repository;	
	
	
	@Override
	public Iterable<Producto> getAllProducto() {
		return repository.findAll();
	}
	
	
	private boolean checkProductoAvailable(Producto producto) throws Exception {
		Optional<Producto> productoFound = repository.findByNombre(producto.getNombre());
		if(productoFound.isPresent()) {
			throw new Exception("Proucto Ya Registrado");
			
		}
		return true;
	}

@Override
public Producto createProducto(Producto producto)
throws Exception {
	
	if(checkProductoAvailable(producto)) {
		
		producto =repository.save(producto);
		
	}
	return producto;
}


@Override
public Optional<Producto> findByNombre(String nombre) {
	// TODO Auto-generated method stub
	return null;
}







@Override
public Producto getProductoById(Long id) throws UsernameOrIdNotFound {
	return repository.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("el id del cliente no exite"));
}

@Override
public Producto updateProducto(Producto productoForm) throws Exception {
	Producto toProducto= getProductoById(productoForm.getId());
	mapProducto(productoForm,toProducto);
	return repository.save(toProducto);
}

protected void mapProducto(Producto from,Producto to) {

to.setNombre(from.getNombre());
to.setCantidad(from.getCantidad());
to.setEstado(from.getEstado());
to.setPrecio(from.getPrecio());




}

@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
@Override
public void deleteProducto(Long id) throws UsernameOrIdNotFound {
	Producto producto= getProductoById(id);
	repository.delete(producto);
	

	
}
}

