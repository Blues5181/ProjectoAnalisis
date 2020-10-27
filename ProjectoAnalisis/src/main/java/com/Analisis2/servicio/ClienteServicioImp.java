package com.Analisis2.servicio;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Analisis2.entity.Cliente;

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


	
	
}
