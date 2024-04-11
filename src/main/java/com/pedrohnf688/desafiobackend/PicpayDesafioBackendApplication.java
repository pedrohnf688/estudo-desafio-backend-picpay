package com.pedrohnf688.desafiobackend;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.config.TopicBuilder;

@EnableJpaAuditing
@SpringBootApplication
public class PicpayDesafioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayDesafioBackendApplication.class, args);
	}

	@Bean
	NewTopic notificacaoTopico() {
		return TopicBuilder.name("transacao-notificacao").build();
	}

}
