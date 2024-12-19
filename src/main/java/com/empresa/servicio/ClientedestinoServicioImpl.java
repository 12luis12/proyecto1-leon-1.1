package com.empresa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidad.Clientedestino;
import com.empresa.repositorio.ClientedestinoRepositorio;
@Service
public class ClientedestinoServicioImpl implements ClientedestinoServicio {
     
	
	@Autowired
	private ClientedestinoRepositorio repositorio;
	@Override
	public List<Clientedestino> listarTodoLosClientedestino() {
		return repositorio.findAll();
	}

}
