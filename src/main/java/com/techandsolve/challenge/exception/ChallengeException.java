/**
 * 
 */
package com.techandsolve.challenge.exception;

/**
 * @author Jperezc
 *
 */
public class ChallengeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7332540502468967894L;
	
	

	/**
	 * @param codigo
	 * @param mensaje
	 */
	public ChallengeException(String mensaje) {
		super(mensaje);
		
	}

	/**
	 * @param mensaje
	 * @param cause
	 */
	public ChallengeException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}

}
