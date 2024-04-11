package com.pedrohnf688.desafiobackend.notificacao;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pedrohnf688.desafiobackend.transacao.Transacao;

@Service
public class NotificacaoProducer {

	private final KafkaTemplate<String, Transacao> kafkaTemplate;

	public NotificacaoProducer(KafkaTemplate<String, Transacao> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void enviarNotificacao(Transacao transacao) {
		kafkaTemplate.send("transacao-notificacao", transacao);
	}

}
