package com.Analisis2.servicio;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.Analisis2.Exception.UsernameOrIdNotFound;
import com.Analisis2.entity.Cliente;
import com.Analisis2.entity.Usuario;
import com.Analisis2.repositorio.ClienteRepo;


@Service
public class ClienteServicioImp implements ClienteServicio {

	@Autowired
	ClienteRepo repository;	
	
	
	@Override
	public Iterable<Cliente> getAllClients() {
		return repository.findAll();
	}
	
	
	private boolean checkClientAvailable(Cliente cliente) throws Exception {
		Optional<Cliente> clientFound = repository.findByNombre(cliente.getNombre());
		if(clientFound.isPresent()) {
			throw new Exception("Cliente Ya Registrado");
			
		}
		return true;
	}

@Override
public Cliente createCliente(Cliente cliente)
throws Exception {
	
	if(checkClientAvailable(cliente)) {
		
		cliente =repository.save(cliente);
		
	}
	return cliente;
}


@Override
public Optional<Cliente> findByNombre(String nombre) {
	// TODO Auto-generated method stub
	return null;
}







@Override
public Cliente getClienteById(Long id) throws UsernameOrIdNotFound {
	return repository.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("el id del cliente no exite"));
}

@Override
public Cliente updateCliente(Cliente clientForm) throws Exception {
	Cliente toCliente= getClienteById(clientForm.getId());
	mapClient(clientForm,toCliente);
	return repository.save(toCliente);
}

protected void mapClient(Cliente from,Cliente to) {
to.setNit(from.getNit());
to.setNombre(from.getNombre());
to.setDirreccion(from.getDirreccion());

}

@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
@Override
public void deleteCliente(Long id) throws UsernameOrIdNotFound {
	Cliente cliente= getClienteById(id);
	repository.delete(cliente);
	
}

	
	
}
