package com.socket.banco.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaldoEntradaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4854176557127150502L;
	private String action;
	private String cardnumber;
	private BigDecimal amount;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
