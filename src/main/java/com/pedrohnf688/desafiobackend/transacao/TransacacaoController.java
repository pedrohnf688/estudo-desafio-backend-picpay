package com.pedrohnf688.desafiobackend.transacao;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransacacaoController {

	private final TransacaoService transacaoService;

	public TransacacaoController(TransacaoService transacaoService) {
		this.transacaoService = transacaoService;
	}

	@GetMapping
	public List<Transacao> list() {
		return this.transacaoService.findAll();
	}

	@PostMapping
	public Transacao criarTransacao(@RequestBody Transacao transacao) {
		return this.transacaoService.criarTransacao(transacao);
	}

}
