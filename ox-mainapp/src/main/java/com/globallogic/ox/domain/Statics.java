package com.globallogic.ox.domain;

import java.io.Serializable;

public class Statics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String number;
    private String time;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
