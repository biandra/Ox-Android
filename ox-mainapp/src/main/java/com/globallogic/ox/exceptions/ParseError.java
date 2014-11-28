package com.globallogic.ox.exceptions;

public class ParseError extends OxException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParseError() {
		super();
	}

	public ParseError(String msg) {
		super(msg);
	
	}
	
	public ParseError(String msg, Exception e) {
		super(msg,e);
		
	}

}
