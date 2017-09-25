package com.arielvieira.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arielvieira.mc.domain.Cliente;
import com.arielvieira.mc.repositories.ClienteRepository;
import com.arielvieira.mc.services.exceptions.ObjetNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjetNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		else {
			return obj;
		}
		
	}
}
