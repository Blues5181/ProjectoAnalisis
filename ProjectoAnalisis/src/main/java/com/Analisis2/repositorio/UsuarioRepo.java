package com.Analisis2.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Analisis2.entity.Usuario;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario,Long> {

	Iterable<Usuario> findAll();

	
}
