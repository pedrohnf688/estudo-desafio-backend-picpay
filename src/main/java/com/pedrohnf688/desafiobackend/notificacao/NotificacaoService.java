package com.pedrohnf688.desafiobackend.notificacao;

import org.springframework.stereotype.Service;

import com.pedrohnf688.desafiobackend.transacao.Transacao;

@Service
public class NotificacaoService {

	private final NotificacaoProducer notificacaoProducer;

	public NotificacaoService(NotificacaoProducer notificacaoProducer) {
		this.notificacaoProducer = notificacaoProducer;
	}

	public void notificacao(Transacao transacao) {
		notificacaoProducer.enviarNotificacao(transacao);
	}
}
