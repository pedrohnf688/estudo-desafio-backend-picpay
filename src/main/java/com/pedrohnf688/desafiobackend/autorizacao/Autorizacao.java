package com.pedrohnf688.desafiobackend.autorizacao;

public record Autorizacao(String message) {

	public boolean isAutorizado() {
		return message.equals("Autorizado");
	}
}
