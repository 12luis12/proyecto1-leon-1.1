package com.empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Cliente;
import com.empresa.repositorio.ClienteRepositorio;

@Service
public class ClienteServicioImpl implements ClienteServicio {
	
	@Autowired
	private ClienteRepositorio repositorio;

	@Override
	public List<Cliente> listarTodoLosCliente() {  //este metodo tb se utiliza para Boleta
		return repositorio.findAll();
	}

	@Override
	public Cliente registraActualizaCliente(Cliente obj) {
		return repositorio.save(obj);
	}

	@Override
	public void eliminaCliente(int id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public List<Cliente> buscaClientePorNombre(String filtro) {
		return repositorio.buscarPorNombre(filtro);
	}

	@Override
	public Optional<Cliente> buscarClientePorId(int id) {
		return repositorio.findById(id);
	}

	

	@Override
	public Cliente findByNombreAndApellido1(String nombre, String apellido) {
		
		return repositorio.findByNombreAndApellido1(nombre, apellido);
	}
	@Override
	 public Cliente guardarActualizarCliente(Cliente cliente) {
	        return repositorio.save(cliente);
	    }

	

	

}
