package org.ucm.tp1.logic.exceptions;

public class InvalidTypeException extends CommandExecuteException {
	private static final long serialVersionUID = 1L;
	
	public InvalidTypeException() { super(); }
	public InvalidTypeException(String message) { super(message); }
	public InvalidTypeException(String message, Throwable cause) { super(message, cause); }
	public InvalidTypeException(Throwable cause) { super(cause); }

}