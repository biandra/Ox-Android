package com.globallogic.ox.exceptions;

public class OxException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public OxException() {
		
	}
	
	public OxException(String msg){
		super(msg);
	}
	
	public OxException(String msg, Exception e){
		super(msg,e);
	}
}
