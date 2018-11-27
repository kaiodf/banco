package com.socket.tcp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SocketEntradaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2482683339717182266L;
	
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
	@Override
	public String toString() {
		return "SocketEntradaDto [action=" + action + ", cardnumber=" + cardnumber + ", amount=" + amount + "]";
	}
}
