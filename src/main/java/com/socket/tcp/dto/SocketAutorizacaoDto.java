package com.socket.tcp.dto;

import java.io.Serializable;

public class SocketAutorizacaoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464089006987706529L;
	
	private String action;
	private String code;
	private String authorization_code;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAuthorization_code() {
		return authorization_code;
	}
	public void setAuthorization_code(String authorization_code) {
		this.authorization_code = authorization_code;
	}
	
	@Override
	public String toString() {
		return "SocketAutorizacaoDto [action=" + action + ", code=" + code + ", authorization_code="
				+ authorization_code + "]";
	}
}
