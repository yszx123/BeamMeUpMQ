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
 * Provide some error codes for 
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public enum BeamMeUpMQError {

	/*===================================
	 * 		GENERAL CODES -> 1 - 100 
	 * ==================================
	 */
	/**
	 * Unknown error
	 */
	UNKNOWN(1,new BeamMeUpMQMessage("bmum.err.unknown","Unknown error"))
	;
	
	/**
	 * An integer error code. It should be UNIQUE for the current enum
	 */
	private Integer errorCode;
	
	/**
	 * An error message
	 */
	private BeamMeUpMQMessage errorMessage;
	
	/**
	 * Constructor 
	 * 
	 * @param errorCode An integer error code. It should be UNIQUE for the current enum
	 * @param errorMessage An error message
	 */
	private BeamMeUpMQError(Integer errorCode, BeamMeUpMQMessage errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Get the error message
	 * 
	 * @return The message code
	 */
	public Integer getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Get the error message associated to the code
	 * 
	 * @return The error message associated to the code
	 */
	public BeamMeUpMQMessage getErrorMessage() {
		return errorMessage;
	}
}
