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

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * A base request params class. To be extended by any other request params
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public abstract class AbstractBaseRequestParams implements Serializable {

	private static final long serialVersionUID = 1788679862908742546L;
	
	private RequestParamsValidator validator;

	/**
	 * Get the validator for the current search params
	 * 
	 * @return The validator for the current search params
	 */
	@JsonIgnore
	public RequestParamsValidator getValidator() {
		return validator;
	}

	/**
	 * Set a new validator
	 * 
	 * @param validator The new validator
	 */
	@JsonIgnore
	public void setValidator(RequestParamsValidator validator) {
		this.validator = validator;
	}
	
	/**
	 * Validate the current search params
	 * 
	 * @param useDefaultValidator Force the use of the default validator. Provide <code>true</code> if no custom validator have been set
	 * @throws ValidationException When the validation fails
	 */
	@JsonIgnore
	public void validate(boolean useDefaultValidator) throws ValidationException {
		if (useDefaultValidator) {
			this.getDefaultSearchParamsValidator().validate();
		} else {
			this.validator.validate();
		}
	}

	/**
	 * Get the default validator for the search params
	 * 
	 * @return The default validator
	 */
	@JsonIgnore
	public abstract RequestParamsValidator getDefaultSearchParamsValidator();
	
	/**
	 * Apply some request specific default values.
	 * 
	 * @param force If <code>true</code> overrides the values coming from the request
	 */
	@JsonIgnore
	public abstract void applyDefaultValues(boolean force);
}
