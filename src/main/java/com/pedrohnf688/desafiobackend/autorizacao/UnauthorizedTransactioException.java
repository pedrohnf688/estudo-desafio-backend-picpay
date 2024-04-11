package com.pedrohnf688.desafiobackend.autorizacao;

public class UnauthorizedTransactioException extends RuntimeException {

	public UnauthorizedTransactioException(String message) {
		super(message);
	}

	
}
