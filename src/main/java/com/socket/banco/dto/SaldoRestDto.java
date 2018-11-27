package com.socket.banco.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SaldoRestDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6401267422744103220L;
	private String cardnumber;
	private BigDecimal availableAmount;
	private List<TransacaoRestDto> listaTransacaoDto;
	
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}
	public List<TransacaoRestDto> getListaTransacaoDto() {
		return listaTransacaoDto;
	}
	public void setListaTransacaoDto(List<TransacaoRestDto> listaTransacaoDto) {
		this.listaTransacaoDto = listaTransacaoDto;
	}

}
