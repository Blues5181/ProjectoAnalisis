package com.Analisis2.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Analisis2.Exception.UsernameOrIdNotFound;
import com.Analisis2.dto.ChangePasswordForm;
import com.Analisis2.entity.Usuario;
import com.Analisis2.repositorio.UsuarioRepo;

@Service
public class UsuarioServicioimp implements UsuarioServicio {

	@Autowired
	UsuarioRepo repository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	
	
	String encodePaasswkrd =bCryptPasswordEncoder.encode(usuario.getPassword());
	usuario.setPassword(encodePaasswkrd);
	usuario = repository.save(usuario);
}
	return usuario;
}

@Override 
public Usuario getUserById(Long id) throws UsernameOrIdNotFound {
return repository.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("el id del usuario no exite"));
	

	
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
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public void deleteUser(Long id) throws UsernameOrIdNotFound {
	Usuario usuario = getUserById(id);
			

repository.delete(usuario);
}
@Override
public Usuario changePassword(ChangePasswordForm form) throws Exception {
	
Usuario usuario = getUserById(form.getId());
	
if( !isLoggedUserADMIN() && form.getCurrentPassword().equals(form.getCurrentPassword())) {
	throw new Exception("Current Password Incorrect.");
}


if(usuario.getPassword().equals(form.getNewPassword())) {
	
	throw new Exception ("El password debe de ser diferente al actual");
}

if(!form.getNewPassword().equals(form.getConfirmPassword())) {
	throw new Exception ("El password no coincide");
	
}
String encodePaasswkrd =bCryptPasswordEncoder.encode(form.getNewPassword());
usuario.setPassword(encodePaasswkrd);
return repository.save(usuario);
}

public boolean isLoggedUserADMIN(){
	 return loggedUserHasRole("ROLE_ADMIN");
	}

	public boolean loggedUserHasRole(String role) {
	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 UserDetails loggedUser = null;
	 Object roles = null; 
	 if (principal instanceof UserDetails) {
	  loggedUser = (UserDetails) principal;
	 
	  roles = loggedUser.getAuthorities().stream()
	    .filter(x -> role.equals(x.getAuthority() ))      
	    .findFirst().orElse(null); //loggedUser = null;
	 }
	 return roles != null ?true :false;
	}

}


