package com.Analisis2.servicio;

import javax.validation.Valid;

import com.Analisis2.entity.Usuario;

public interface UsuarioServicio {

	public Iterable<Usuario> getAllUsers();

	public Usuario createUsuario(Usuario usuario) throws Exception;
	
	
}
