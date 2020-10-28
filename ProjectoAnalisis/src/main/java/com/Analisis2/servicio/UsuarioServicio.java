package com.Analisis2.servicio;

import javax.validation.Valid;

import com.Analisis2.entity.Usuario;

public interface UsuarioServicio {

	public Iterable<Usuario> getAllUsers();

	public Usuario createUsuario(Usuario usuario) throws Exception;
	
	public Usuario getUserById(Long id) throws Exception;

public Usuario updateUser(Usuario usuario) throws Exception;

public void deleteUser(Long id) throws Exception;

}
