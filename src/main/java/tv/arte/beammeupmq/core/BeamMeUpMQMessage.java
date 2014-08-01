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

/**
 * The main BeamMeUpMQ Exception class. Try to wrap up all unknown exceptions when we need to 
 * do some special treatment or inject information
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class BeamMeUpMQMessage implements Serializable {
	
	private static final long serialVersionUID = -1306921021877444296L;

	/**
	 * A key to be uses with MessageSource for internationalization purposes
	 */
	private String i18nMessageKey;
	
	/**
	 * A default message to use while the internationalization is not used or just in case we want an EN message
	 */
	private String defaultMessage;

	/**
	 * Fully featured constructor
	 * 
	 * @param i18nMessageKey The internationalization key
	 * @param defaultMessage The default message (in English)
	 */
	public BeamMeUpMQMessage(String i18nMessageKey, String defaultMessage) {
		super();
		this.i18nMessageKey = i18nMessageKey;
		this.defaultMessage = defaultMessage;
	}

	/**
	 * Get the internationalization key of the message
	 * 
	 * @return The internationalization key of the message
	 */
	public String getI18nMessageKey() {
		return i18nMessageKey;
	}

	/**
	 * Set the internationalization key of the message
	 */
	public void setI18nMessageKey(String i18nMessageKey) {
		this.i18nMessageKey = i18nMessageKey;
	}

	/**
	 * Get the default message
	 * 
	 * @return The default message
	 */
	public String getDefaultMessage() {
		return defaultMessage;
	}

	/**
	 * Set the default message
	 */
	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}	
}
