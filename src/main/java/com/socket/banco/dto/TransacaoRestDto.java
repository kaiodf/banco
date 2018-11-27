package com.socket.banco.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransacaoRestDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -233614051998720464L;
	private Date data;
	private BigDecimal amount;

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
