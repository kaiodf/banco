package com.socket.banco.controller;

import java.math.BigDecimal;

import com.socket.banco.dto.SaldoRestDto;
import com.socket.banco.model.Saldo;
import com.socket.tcp.dto.SocketAutorizacaoDto;
import com.socket.tcp.dto.SocketEntradaDto;

/**
 * The Interface SaldoController.
 */
public interface SaldoController {

	/**
	 * Consultar saldo.
	 *
	 * @param cardNumber the card number
	 * @return the saldo dto
	 */
	SaldoRestDto consultar(String cardNumber);

	/**
	 * Withdraw.
	 *
	 * @param socketEntradaDto the socket entrada dto
	 * @return the socket autorizacao dto
	 */
	SocketAutorizacaoDto withdraw(SocketEntradaDto socketEntradaDto);

	
	/**
	 * Atualizar.
	 *
	 * @param saldo the saldo
	 * @param saldoRetirado the saldo retirado
	 * @return the saldo rest dto
	 */
	SaldoRestDto atualizar(Saldo saldo, BigDecimal saldoRetirado);
}
