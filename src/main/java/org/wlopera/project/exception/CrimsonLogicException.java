package org.wlopera.project.exception;

/**
 * Manejo de execepciones del modulo.
 * 
 * @author William Lopera
 */
public class CrimsonLogicException extends Exception {

	private static final long serialVersionUID = 6166566900550267920L;

	public CrimsonLogicException(String message) {
		super(message);
	}

	public CrimsonLogicException(String message, Throwable t) {
		super(message, t);
	}
	
}
