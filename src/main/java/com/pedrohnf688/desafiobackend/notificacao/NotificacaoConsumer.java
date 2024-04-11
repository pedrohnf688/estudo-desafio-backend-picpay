package com.pedrohnf688.desafiobackend.notificacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.pedrohnf688.desafiobackend.autorizacao.AutorizacaoService;
import com.pedrohnf688.desafiobackend.transacao.Transacao;

@Service
public class NotificacaoConsumer {

	private RestClient restClient;
	private static final Logger logger = LoggerFactory.getLogger(AutorizacaoService.class);

	public NotificacaoConsumer(RestClient.Builder builder) {
		this.restClient = builder.baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6").build();
	}

	@KafkaListener(topics = "transacao-notificacao", groupId = "backend-desafio")
	public void receberNotificacao(Transacao transacao) {
		logger.info("Notificando Transacao: {} ...", transacao);
		
		var response = restClient.get().retrieve().toEntity(Notificacao.class);

		if (response.getStatusCode().isError() || !response.getBody().message()) {
			throw new NotificationException("Erro ao enviar notificação!");
		}
		
		logger.info("Notificando foi enviada {} ...", response.getBody());

	}

}
