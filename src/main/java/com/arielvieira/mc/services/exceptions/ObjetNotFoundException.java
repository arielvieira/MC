package com.arielvieira.mc.services.exceptions;

public class ObjetNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjetNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjetNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
