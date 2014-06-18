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


import java.util.ArrayList;
import java.util.Collection;

/**
 * A validation exception
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class ValidationException extends BeamMeUpMQException {

	private static final long serialVersionUID = -4983068645300321751L;

	private Object objectToValidate;

	private Collection<BeamMeUpMQError> validationErrorMessages;
	
	/**
	 * Constructor
	 * 
	 * @param objectToValidate
	 */
	public ValidationException(Object objectToValidate) {
		super();
		this.objectToValidate = objectToValidate;
	}

	/**
	 * Get the object that is currently validated
	 * 
	 * @return The object that is currently validated
	 */
	public Object getObjectToValidate() {
		return objectToValidate;
	}

	/**
	 * Get all error messages. 
	 * 
	 * @return All error messages. Could be <code>null</code>.
	 */
	public Collection<BeamMeUpMQError> getValidationErrorMessages() {
		return validationErrorMessages;
	}

	/**
	 * Set error messages
	 * 
	 * @param validationErrorMessages Ne error messages to set
	 */
	public void setValidationErrorMessages(
			Collection<BeamMeUpMQError> validationErrorMessages) {
		this.validationErrorMessages = validationErrorMessages;
	}
	
	/**
	 * Add new error message. If messages have not been initialized via {@link #setValidationErrorMessages(Collection)}}
	 * the default collection will be {@link ArrayList}} 
	 * 
	 * @param errorMessage
	 */
	public void addValidationMessage(BeamMeUpMQError errorMessage) {
		if (validationErrorMessages == null)  {
			validationErrorMessages = new ArrayList<BeamMeUpMQError>();
		}
		
		validationErrorMessages.add(errorMessage);
	}
}
