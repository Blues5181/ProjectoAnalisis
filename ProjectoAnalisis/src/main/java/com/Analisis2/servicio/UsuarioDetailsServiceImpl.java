package com.Analisis2.servicio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Analisis2.entity.Role;
import com.Analisis2.repositorio.UsuarioRepo;

@Service
@Transactional
public class UsuarioDetailsServiceImpl implements UserDetailsService  {

@Autowired
UsuarioRepo usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.Analisis2.entity.Usuario appUser = usuarioRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Login suario Invalido"));
		
	Set<GrantedAuthority>	  grantList = new HashSet<GrantedAuthority>();
		for (Role role: appUser.getRoles())
		{
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
            grantList.add(grantedAuthority);
            
           
			
		}
		//UserDetails=clase de spring securyti
		//User se importa de spring security
		UserDetails usuario = (UserDetails) new User(username,appUser.getPassword(),grantList);
		
		return usuario;
	}

}
