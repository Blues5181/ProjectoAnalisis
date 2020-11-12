package com.Analisis2.servicio;

import java.util.Optional;



import com.Analisis2.Exception.UsernameOrIdNotFound;
import com.Analisis2.entity.Cliente;






public interface ClienteServicio {

	public Iterable<Cliente> getAllClients();

	
	public Optional<Cliente> findByNombre(String nombre);
	public Cliente getClienteById(Long id) throws Exception;
	public Cliente createCliente(Cliente cliente) throws Exception;
	public Cliente updateCliente(Cliente cliente) throws Exception;

	public void deleteCliente(Long id) throws UsernameOrIdNotFound;
}
