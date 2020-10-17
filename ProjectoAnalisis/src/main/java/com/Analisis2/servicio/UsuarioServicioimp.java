package com.Analisis2.servicio;

import java.util.Optional;

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
private boolean checkUsernameAvailable(Usuario usuario) throws Exception {
	Optional<Usuario> userFound = repository.findByUsername(usuario.getUsername());
	if(userFound.isPresent()) {
		throw new Exception("Usuario no disponible");
		
	}
	return true;
}

private boolean checkPasswordValid(Usuario usuario) throws Exception {
	
	if(!usuario.getPassword().equals(usuario.getConfirmPassword())){
		
		throw new Exception("el password no coincide");
		
	}
	return true;
}
@Override
public Usuario createUsuario(Usuario usuario) throws Exception {
if(checkUsernameAvailable(usuario) && checkPasswordValid(usuario)) {
	
	usuario = repository.save(usuario);
}
	return usuario;
}
}
