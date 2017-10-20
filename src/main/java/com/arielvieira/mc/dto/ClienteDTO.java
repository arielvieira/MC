package com.arielvieira.mc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arielvieira.mc.domain.Cliente;


public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	
	@NotEmpty(message="Nome Obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 carecteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Email(message="email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
