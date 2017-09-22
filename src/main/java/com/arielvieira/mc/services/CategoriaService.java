package com.arielvieira.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arielvieira.mc.domain.Categoria;
import com.arielvieira.mc.repositories.CategoriaRepository;
import com.arielvieira.mc.services.exceptions.ObjetNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjetNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName());
		}
		else {
			return obj;
		}
		
	}
}
