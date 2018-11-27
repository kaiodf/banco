package com.socket.banco.rest.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socket.banco.controller.SaldoController;
import com.socket.banco.dto.SaldoRestDto;

import io.swagger.annotations.Api;

/**
 * The Class SaldoRest.
 */
@Api
@RestController
@RequestMapping(value = "/saldo")
public class SaldoRest {
	
	/** The saldo controller. */
	@Autowired
	SaldoController saldoController;

	/**
	 * Serviço de consultar o saldo pelo cardNumber.
	 *
	 * @param cardNumber the card number
	 * @return the response entity
	 */
	@RequestMapping(value = "{cardNumber}", method = RequestMethod.GET)
	public ResponseEntity<SaldoRestDto> consultar(@PathVariable("cardNumber") String cardNumber){
		SaldoRestDto dto = saldoController.consultar(cardNumber);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
