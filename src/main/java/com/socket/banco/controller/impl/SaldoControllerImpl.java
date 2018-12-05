package com.socket.banco.controller.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socket.banco.controller.SaldoController;
import com.socket.banco.dto.SaldoRestDto;
import com.socket.banco.dto.TransacaoRestDto;
import com.socket.banco.model.Saldo;
import com.socket.banco.model.Transacao;
import com.socket.banco.repository.SaldoRepository;
import com.socket.banco.repository.TransacaoRepository;
import com.socket.tcp.dto.SocketAutorizacaoDto;
import com.socket.tcp.dto.SocketEntradaDto;

@Service
public class SaldoControllerImpl implements SaldoController{
	
	private static final String APROVADA = "00";
	private static final String SALDO_INSUFICIENTE = "51";
	private static final String CONTA_INVALIDA = "14";
	private static final String ERRO_PROCESSAMENTO = "96";
	
	@Autowired
	SaldoRepository saldoRepository;
	
	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Override
	@Transactional
	public SaldoRestDto consultar(String cardNumber) {
		return converterToDto(saldoRepository.consultar(cardNumber));
	}

	private SaldoRestDto converterToDto(Saldo saldo) {
		SaldoRestDto dto = new SaldoRestDto();
		dto.setAvailableAmount(saldo.getAvailableAmount());
		dto.setCardnumber(saldo.getCardNumber());
		dto.setListaTransacaoDto(converterListaTransacaoDto(saldo.getListaTransacao()));
		return dto;
	}

	private List<TransacaoRestDto> converterListaTransacaoDto(List<Transacao> listaTransacao) {
		List<TransacaoRestDto> lista = new ArrayList<>();
		for (Transacao transacao : listaTransacao) {
			TransacaoRestDto dto = new TransacaoRestDto();
			dto.setAmount(transacao.getAmount());
			dto.setData(transacao.getData());
			lista.add(dto);
		}
		
		return lista;
	}

	@Override
	public SocketAutorizacaoDto withdraw(SocketEntradaDto socketEntradaDto) {
		SocketAutorizacaoDto dto = new SocketAutorizacaoDto();
		Saldo saldo = saldoRepository.consultar(socketEntradaDto.getCardnumber());
		dto.setAction(socketEntradaDto.getAction());
		dto.setCode(ERRO_PROCESSAMENTO);
		dto.setAuthorization_code(gerarCodeAutorizacao().toString().substring(1, 7));
		
		if(saldo==null) {
			dto.setCode(CONTA_INVALIDA);
			return dto;
		}
		if(verificarSaldo(saldo.getAvailableAmount(), socketEntradaDto.getAmount())) {
			saldo.setAvailableAmount(saldo.getAvailableAmount().subtract(socketEntradaDto.getAmount()));
			atualizar(saldo,socketEntradaDto.getAmount());
			dto.setCode(APROVADA);
		}else{
			dto.setCode(SALDO_INSUFICIENTE);
		}
		
		return dto;
	}
	
	private Long gerarCodeAutorizacao() {
		Random radom  = new Random();
	    Long numeroTmp = new Long(0);
	    for(int i=0;i<10; i++) {
	        numeroTmp=radom.nextLong();
	    }
		return numeroTmp;
	}

	private Boolean verificarSaldo(BigDecimal availableAmount, BigDecimal amount) {
		return availableAmount.compareTo(amount)==1 || availableAmount.compareTo(amount)==0;
	}

	@Override
	public SaldoRestDto atualizar(Saldo saldo, BigDecimal saldoRetirado) {
		Transacao transacao = new Transacao();
		transacao.setAmount(saldoRetirado);
		transacao.setData(new Date());
		List<Transacao> listaTransacao = saldo.getListaTransacao();
		listaTransacao.add(transacao);
		return converterToDto(saldoRepository.save(saldo));
	}

}
