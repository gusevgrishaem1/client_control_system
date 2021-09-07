package com.techls.fdoClientsManagerWebApp.exceptions;


public class EmptyDirectoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public EmptyDirectoryException(String msg) {
		super(msg);
		setMessage(msg);
	}
	
	@Override 
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message; 
	}
	
}
