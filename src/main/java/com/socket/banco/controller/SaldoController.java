package com.socket.banco.controller;

import com.socket.banco.dto.SaldoRestDto;
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

}
