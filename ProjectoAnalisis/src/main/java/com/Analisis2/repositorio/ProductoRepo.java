package com.Analisis2.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.Analisis2.entity.Producto;



@Repository
public interface ProductoRepo extends CrudRepository<Producto,Long>{
	
	Iterable<Producto> findAll();

	public Optional<Producto> findByNombre(String nombre);
	
	
}
