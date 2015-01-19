package com.globallogic.ox.domain;

import java.io.Serializable;

public class Statics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String number;
    private int time;
    private int progress;
    
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
}
