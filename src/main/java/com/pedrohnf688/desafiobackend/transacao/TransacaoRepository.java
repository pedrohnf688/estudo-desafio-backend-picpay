package com.pedrohnf688.desafiobackend.transacao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends ListCrudRepository<Transacao, Long> {

}
