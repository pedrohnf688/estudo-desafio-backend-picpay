package com.pedrohnf688.desafiobackend.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TRANSACAO")
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long pagador;
	private Long recebedor;
	private BigDecimal valor;
	@CreatedDate
	private LocalDateTime dataCriacao;

	@Override
	public String toString() {
		return "[ID:" + this.id + ", Pagador: " + this.pagador + ", Recebedor: " + this.recebedor + ", Valor:"
				+ this.valor + ", Data de criação: " + this.dataCriacao + "]";
	}
}
