package com.socket.banco.controller.impl;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.socket.banco.model.Saldo;
import com.socket.banco.model.Transacao;
import com.socket.banco.repository.SaldoRepository;
import com.socket.tcp.dto.SocketEntradaDto;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class SaldoControllerImplTest {

	@Tested
	SaldoControllerImpl saldoControllerImpl;
	
	@Injectable
	SaldoRepository saldoRepository;
	
	@Test
	public void consultar() {
		new Expectations() {
			{
				saldoRepository.consultar(anyString);
				result = criarSaldo();
			}

		};
		assertNotNull(saldoControllerImpl.consultar("123"));
	}
	
	@Test
	public void withdraw() {
		new Expectations() {
			{
				saldoRepository.consultar(anyString);
				result = criarSaldo();
				
				saldoRepository.save(withInstanceOf(Saldo.class));
				result = criarSaldo();
			}
		};
		
		assertNotNull(saldoControllerImpl.withdraw(criarSocketEntradaDto()));
	}
	
	private SocketEntradaDto criarSocketEntradaDto() {
		SocketEntradaDto dto = new SocketEntradaDto();
		dto.setAction("withdraw");
		dto.setAmount(BigDecimal.ZERO);
		dto.setCardnumber("123");
		return dto;
	}

	private Saldo criarSaldo() {
		Saldo saldo = new Saldo();
		saldo.setAvailableAmount(BigDecimal.TEN);
		saldo.setCardNumber("123");
		saldo.setId(Long.valueOf(1));
		saldo.setListaTransacao(criarListaTransacao());
		return saldo;
	}

	private List<Transacao> criarListaTransacao() {
		List<Transacao> lista = new ArrayList<>();
		lista.add(criarTransacao());
		return lista;
	}

	private Transacao criarTransacao() {
		Transacao transacao = new Transacao();
		transacao.setAmount(BigDecimal.TEN);
		transacao.setData(new Date());
		transacao.setId(Long.valueOf(1));
		return transacao;
	}
}
