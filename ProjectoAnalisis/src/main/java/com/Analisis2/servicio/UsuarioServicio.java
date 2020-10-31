package com.Analisis2.servicio;

import javax.validation.Valid;

import com.Analisis2.Exception.UsernameOrIdNotFound;
import com.Analisis2.dto.ChangePasswordForm;
import com.Analisis2.entity.Usuario;

public interface UsuarioServicio {

	public Iterable<Usuario> getAllUsers();

	public Usuario createUsuario(Usuario usuario) throws Exception;
	
	public Usuario getUserById(Long id) throws Exception;

public Usuario updateUser(Usuario usuario) throws Exception;

public void deleteUser(Long id) throws UsernameOrIdNotFound;
public Usuario changePassword(ChangePasswordForm form) throws Exception;

}
