package com.Analisis2.repositorio;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Analisis2.entity.Cliente;

@Repository
public interface ClienteRepo extends CrudRepository<Cliente,Long> {

	
	Iterable<Cliente> findAll();

	public Optional<Cliente> findByNombre(String nombre);
}
