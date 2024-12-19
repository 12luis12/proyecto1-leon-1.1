package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.empresa.entidad.Cliente;


public interface ClienteServicio {
	public abstract List<Cliente> listarTodoLosCliente();
	public abstract Cliente registraActualizaCliente(Cliente obj);
	public abstract void eliminaCliente(int id);
	public abstract List<Cliente> buscaClientePorNombre(String filtro);
	public abstract Optional<Cliente> buscarClientePorId(int id);
	
	public abstract Cliente findByNombreAndApellido1(String nombre, String apellido);
	public abstract Cliente guardarActualizarCliente(Cliente cliente);
	
	

}
