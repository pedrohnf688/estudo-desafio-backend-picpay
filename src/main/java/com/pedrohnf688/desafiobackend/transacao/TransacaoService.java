package com.pedrohnf688.desafiobackend.transacao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.desafiobackend.autorizacao.AutorizacaoService;
import com.pedrohnf688.desafiobackend.carteira.Carteira;
import com.pedrohnf688.desafiobackend.carteira.CarteiraRepository;
import com.pedrohnf688.desafiobackend.carteira.TipoCarteira;
import com.pedrohnf688.desafiobackend.notificacao.NotificacaoService;

@Service
public class TransacaoService {

	private final CarteiraRepository carteiraRepository;
	private final TransacaoRepository transacaoRepository;
	private final AutorizacaoService autorizacaoService;
	private final NotificacaoService notificacaoService;

	public TransacaoService(CarteiraRepository carteiraRepository, TransacaoRepository transacaoRepository,
			AutorizacaoService autorizacaoService, NotificacaoService notificacaoService) {
		this.carteiraRepository = carteiraRepository;
		this.transacaoRepository = transacaoRepository;
		this.autorizacaoService = autorizacaoService;
		this.notificacaoService = notificacaoService;
	}
	
	@Transactional
	public Transacao criarTransacao(Transacao transacao) {

		validar(transacao);

		var novaTransacao = this.transacaoRepository.save(transacao);

		var carteiraPagador = this.carteiraRepository.findById(transacao.getPagador()).get();
		carteiraPagador.setSaldo(carteiraPagador.getSaldo().subtract(novaTransacao.getValor()));
		this.carteiraRepository.save(carteiraPagador);

		var carteiraRecebedor = this.carteiraRepository.findById(transacao.getRecebedor()).get();
		carteiraRecebedor.setSaldo(carteiraRecebedor.getSaldo().add(novaTransacao.getValor()));
		this.carteiraRepository.save(carteiraRecebedor);
		
		this.autorizacaoService.autorizacao(transacao);

		this.notificacaoService.notificacao(transacao);

		return novaTransacao;
	}

	private void validar(Transacao transacao) {
		carteiraRepository.findById(transacao.getRecebedor())
				.map(recebedor -> carteiraRepository.findById(transacao.getPagador())
						.map(pagador -> isTransacaoValida(transacao, pagador) ? transacao : null).orElseThrow(
								() -> new InvalidTransactionException("Transação inválida -%s".formatted(transacao.toString()))))
				.orElseThrow(() -> new InvalidTransactionException("Transação inválida -%s".formatted(transacao.toString())));

	}

	private boolean isTransacaoValida(Transacao transacao, Carteira pagador) {
		return pagador.getTipo() == TipoCarteira.COMUM.getValor() && pagador.getSaldo().compareTo(transacao.getValor()) >= 0
				&& !pagador.getId().equals(transacao.getRecebedor());
	}

	public List<Transacao> findAll() {
		return this.transacaoRepository.findAll();
	}
}
