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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A standard response message holder for the BeamMeUpMQ project
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class BeamMeUpMQStandardResponse implements Serializable {

	private static final long serialVersionUID = 8696104303890709284L;

	/**
	 * If the operation is an success or not
	 */
	private Boolean success;
	
	/**
	 * Holds messages that do not need to be identified
	 */
	private List<BeamMeUpMQMessage> infoMessages;
	
	/**
	 * Holds all messages related to errors
	 */
	private List<BeamMeUpMQError> errorMessages;
	
	/**
	 * Default constructor that provides a successful operation information
	 */
	public BeamMeUpMQStandardResponse() {
		this.success = true;
	}
	
	/**
	 * Constructor
	 * 
	 * @param success Indicates if the response is an success
	 * @param infoMessages The information messages
	 */
	public BeamMeUpMQStandardResponse(Boolean success,
			List<BeamMeUpMQMessage> infoMessages) {
		super();
		this.success = success;
		this.infoMessages = infoMessages;
	}

	/**
	 * Constructor
	 * 
	 * @param success Indicates if the response is an success
	 * @param infoMessages The information messages
	 * @param errorMessages The error messages
	 */
	public BeamMeUpMQStandardResponse(boolean success,
			List<BeamMeUpMQMessage> infoMessages,
			List<BeamMeUpMQError> errorMessages) {
		super();
		this.success = success;
		this.infoMessages = infoMessages;
		this.errorMessages = errorMessages;
	}

	/**
	 * Verifies if the response is a success
	 * 
	 * @return <code>true</code> if the response is a success, <code>false</code> otherwise 
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * Set the response status
	 * 
	 * @param success The response status (<code>true</code> corresponds to success)
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * Get the list of info messages
	 * 
	 * @return The list of info messages
	 */
	public List<BeamMeUpMQMessage> getInfoMessages() {
		return infoMessages;
	}

	/**
	 * Set a new list of info messages
	 * 
	 * @param infoMessages A new list of info messages
	 */
	public void setInfoMessages(List<BeamMeUpMQMessage> infoMessages) {
		this.infoMessages = infoMessages;
	}

	/**
	 * Get the list of error messages
	 * 
	 * @return The list of error messages
	 */
	public List<BeamMeUpMQError> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Set a new list of error messages
	 * 
	 * @param errorMessages A new list of error messages
	 */
	public void setErrorMessages(List<BeamMeUpMQError> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	/**
	 * Add a new info message
	 * 
	 * @param i18nMessageKey The internationalization key of the message (To be used with MessageSources)
	 * @param defaultMessage The default message
	 */
	public void addInfoMessage(String i18nMessageKey, String defaultMessage) {
		if (this.infoMessages == null) {
			this.infoMessages = new ArrayList<BeamMeUpMQMessage>(2);
		}
		
		this.infoMessages.add(new BeamMeUpMQMessage(i18nMessageKey, defaultMessage));
	}
	
	/**
	 * Add new error message
	 * 
	 * @param errorMessage A predefined BeamMeUpMQ error message
	 */
	public void addErrorMessage(BeamMeUpMQError errorMessage) {
		if (this.errorMessages == null) {
			this.errorMessages = new ArrayList<BeamMeUpMQError>(2);
		}
		
		this.errorMessages.add(errorMessage);
	}
}
