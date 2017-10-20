package com.arielvieira.mc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.arielvieira.mc.domain.Cliente;
import com.arielvieira.mc.dto.ClienteDTO;
import com.arielvieira.mc.repositories.ClienteRepository;
import com.arielvieira.mc.services.exceptions.DataIntegrityException;
import com.arielvieira.mc.services.exceptions.ObjetNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjetNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		else {
			return obj;
		}
		
	}

	public Cliente update(Cliente obj) {
		Cliente newObt = find(obj.getId());
		updateDate(newObt, obj);
		return repo.save(newObt);
	}	

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("nao é possivel excluir porque ha entidades relacionadas");
		}
		
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void updateDate(Cliente newObt, Cliente obj) {
		newObt.setNome(obj.getNome());
		newObt.setEmail(obj.getEmail());
	}
}
