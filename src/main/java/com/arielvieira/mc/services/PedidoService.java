package com.arielvieira.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arielvieira.mc.domain.Pedido;
import com.arielvieira.mc.repositories.PedidoRepository;
import com.arielvieira.mc.services.exceptions.ObjetNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjetNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName());
		}
		else {
			return obj;
		}
		
	}
}
