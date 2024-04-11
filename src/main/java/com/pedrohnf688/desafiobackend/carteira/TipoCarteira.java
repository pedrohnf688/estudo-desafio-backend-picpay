package com.pedrohnf688.desafiobackend.carteira;

public enum TipoCarteira {
	COMUM(1), LOGISTA(2);

	private int valor;

	private TipoCarteira(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

}
