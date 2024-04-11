package com.pedrohnf688.desafiobackend.autorizacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.pedrohnf688.desafiobackend.transacao.Transacao;

@Service
public class AutorizacaoService {

	private RestClient restClient;
	private static final Logger logger = LoggerFactory.getLogger(AutorizacaoService.class);

	public AutorizacaoService(RestClient.Builder restClient) {
		this.restClient = restClient.baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
	}

	public void autorizacao(Transacao transacao) {
		logger.info("Autorizacao Transacao: {}", transacao.toString());

		var response = restClient.get().retrieve().toEntity(Autorizacao.class);

		if (response.getStatusCode().isError() || !response.getBody().isAutorizado()) {
			throw new UnauthorizedTransactioException("Transação não autorizada.");
		}
		
		logger.info("Transacao Autorizada: {}", transacao.toString());
	}
}
