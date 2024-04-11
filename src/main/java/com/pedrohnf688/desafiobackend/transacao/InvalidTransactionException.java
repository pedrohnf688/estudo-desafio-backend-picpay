package com.pedrohnf688.desafiobackend.transacao;

public class InvalidTransactionException extends RuntimeException {

	public InvalidTransactionException(String message) {
		super(message);
	}

}
