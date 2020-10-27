package com.Analisis2.servicio;

import java.util.Optional;

import javax.validation.Valid;

import com.Analisis2.entity.Cliente;




public interface ClienteServicio {

	public Iterable<Cliente> getAllClients();

	
	public Optional<Cliente> findByNombre(String nombre);
	
	public Cliente createCliente(Cliente cliente) throws Exception;
}
