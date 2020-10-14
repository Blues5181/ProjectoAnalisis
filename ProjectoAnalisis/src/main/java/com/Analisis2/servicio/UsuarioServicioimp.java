package com.Analisis2.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Analisis2.entity.Usuario;
import com.Analisis2.repositorio.UsuarioRepo;

@Service
public class UsuarioServicioimp implements UsuarioServicio {

	@Autowired
	UsuarioRepo repository;
	
	
	@Override
	public Iterable<Usuario> getAllUsers() {
		
		return repository.findAll();
	}

}
