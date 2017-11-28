package com.arielvieira.mc.services;

import org.springframework.mail.SimpleMailMessage;

import com.arielvieira.mc.domain.Cliente;
import com.arielvieira.mc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
		
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
