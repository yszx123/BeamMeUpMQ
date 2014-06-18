package tv.arte.beammeupmq.core;

/*
 * #%L
 * BeamMeUpMQ
 * %%
 * Copyright (C) 2014 ARTE G.E.I.E
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of The MIT License (MIT) as published by the Open Source 
 * Initiative.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See The 
 * MIT License (MIT) for more details.
 * 
 * You should have received a copy of The MIT License (MIT) 
 * along with this program.  If not, see <http://opensource.org/licenses/MIT>
 * #L%
 */



/**
 * The main BeamMeUpMQ Exception class. Try to wrap up all unknown exceptions when we need to 
 * do some special treatment or inject information
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class BeamMeUpMQException extends Exception {

	private static final long serialVersionUID = 3275261731803559282L;

	private BeamMeUpMQMessage errorMessage;
	
	/**
	 * @see Exception#Exception()
	 */
	public BeamMeUpMQException() {
		super();
	}
	
	/**
	 * Constructor 
	 * 
	 * @param errorMessage The error message
	 */
	public BeamMeUpMQException(BeamMeUpMQMessage errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public BeamMeUpMQException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public BeamMeUpMQException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(Throwable)
	 */
	public BeamMeUpMQException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause
	 * @param errorMessage The error message
	 */
	public BeamMeUpMQException(Throwable cause, BeamMeUpMQMessage errorMessage) {
		super(cause);
		this.errorMessage = errorMessage;
	}

	/**
	 * Get the error message
	 * 
	 * @return The error message
	 */
	public BeamMeUpMQMessage getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Set the error message
	 * 
	 * @param errorMessage The new error message
	 */
	public void setErrorMessage(BeamMeUpMQMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
}
