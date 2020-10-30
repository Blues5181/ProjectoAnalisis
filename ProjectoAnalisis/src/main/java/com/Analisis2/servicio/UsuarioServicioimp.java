package com.Analisis2.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Analisis2.dto.ChangePasswordForm;
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
	if(usuario.getConfirmPassword()==null || usuario.getConfirmPassword().isEmpty()) {
		throw new Exception("Es necesario Confirmar el password");
		
		
	}
	
	
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

@Override 
public Usuario getUserById(Long id) throws Exception {
return repository.findById(id).orElseThrow(() -> new Exception("Usuario no existe"));
	

	
}
@Override
public Usuario updateUser(Usuario fromUser) throws Exception {
	Usuario toUser= getUserById(fromUser.getId());
	mapUser(fromUser,toUser);
	return repository.save(toUser);
	
}

//actualizamos todo menos el password
protected void mapUser(Usuario from,Usuario to) {
to.setUsername(from.getUsername());
to.setFirstName(from.getFirstName());
to.setLastName(from.getLastName());
to.setEmail(from.getEmail());
to.setRoles(from.getRoles());
	
}

public void deleteUser(Long id) throws Exception {
	Usuario usuario = getUserById(id);
			

repository.delete(usuario);
}
@Override
public Usuario changePassword(ChangePasswordForm form) throws Exception {
	
Usuario usuario = getUserById(form.getId());
	
if(!usuario.getPassword().equals(form.getCurrentPassword())) {
	
	throw new Exception ("Password Actual incorrecto");
	
}
if(usuario.getPassword().equals(form.getNewPassword())) {
	
	throw new Exception ("El password debe de ser diferente al actual");
}

if(!form.getNewPassword().equals(form.getConfirmPassword())) {
	throw new Exception ("El password no coincide");
	
}
usuario.setPassword(form.getNewPassword());
return repository.save(usuario);
}
}


