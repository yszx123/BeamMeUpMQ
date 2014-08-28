package tv.arte.beammeupmq.core.amqp.rabbit;

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


import com.fasterxml.jackson.annotation.JsonIgnore;

import tv.arte.beammeupmq.core.AbstractBaseRequestParams;
import tv.arte.beammeupmq.core.RequestParamsValidator;
import tv.arte.beammeupmq.core.ValidationException;


/**
 * RabbitMQ specific request paramps
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class RabbitMQRequestParams extends AbstractBaseRequestParams {

	private static final long serialVersionUID = -4365322636036657729L;
	
	private String sourceHost;
	private Integer sourcePort;
	private String sourceUsername;
	private String sourcePassword;
	private String sourceVirtualHost;
	private String sourceExchange;
	private String sourceRoutingKey;
	private String sourceQueue;
	
	private String destinationHost;
	private Integer destinationPort;
	private String destinationUsername;
	private String destinationPassword;
	private String destinationVirtualHost;
	private String destinationExchange;
	private String destinationRoutingKey;
	private String destinationQueue;
	
	/**
	 * Requeue the message (or not)
	 */
	private Boolean requeue;
	/**
	 * Number of concerned messages
	 */
	private Integer number;
	
	public RabbitMQRequestParams() {
	}
		
	public String getSourceHost() {
		return sourceHost;
	}

	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	public Integer getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getSourceUsername() {
		return sourceUsername;
	}

	public void setSourceUsername(String sourceUsername) {
		this.sourceUsername = sourceUsername;
	}

	public String getSourcePassword() {
		return sourcePassword;
	}

	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = sourcePassword;
	}

	public String getSourceVirtualHost() {
		return sourceVirtualHost;
	}

	public void setSourceVirtualHost(String sourceVirtualHost) {
		this.sourceVirtualHost = sourceVirtualHost;
	}

	public String getSourceExchange() {
		return sourceExchange;
	}

	public void setSourceExchange(String sourceExchange) {
		this.sourceExchange = sourceExchange;
	}

	public String getSourceRoutingKey() {
		return sourceRoutingKey;
	}

	public void setSourceRoutingKey(String sourceRoutingKey) {
		this.sourceRoutingKey = sourceRoutingKey;
	}

	public String getSourceQueue() {
		return sourceQueue;
	}

	public void setSourceQueue(String sourceQueue) {
		this.sourceQueue = sourceQueue;
	}

	public String getDestinationHost() {
		return destinationHost;
	}

	public void setDestinationHost(String destinationHost) {
		this.destinationHost = destinationHost;
	}

	public Integer getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(Integer destinationPort) {
		this.destinationPort = destinationPort;
	}

	public String getDestinationUsername() {
		return destinationUsername;
	}

	public void setDestinationUsername(String destinationUsername) {
		this.destinationUsername = destinationUsername;
	}

	public String getDestinationPassword() {
		return destinationPassword;
	}

	public void setDestinationPassword(String destinationPassword) {
		this.destinationPassword = destinationPassword;
	}

	public String getDestinationVirtualHost() {
		return destinationVirtualHost;
	}

	public void setDestinationVirtualHost(String destinationVirtualHost) {
		this.destinationVirtualHost = destinationVirtualHost;
	}

	public String getDestinationExchange() {
		return destinationExchange;
	}

	public void setDestinationExchange(String destinationExchange) {
		this.destinationExchange = destinationExchange;
	}

	public String getDestinationRoutingKey() {
		return destinationRoutingKey;
	}

	public void setDestinationRoutingKey(String destinationRoutingKey) {
		this.destinationRoutingKey = destinationRoutingKey;
	}

	public String getDestinationQueue() {
		return destinationQueue;
	}

	public void setDestinationQueue(String destinationQueue) {
		this.destinationQueue = destinationQueue;
	}

	public Boolean getRequeue() {
		return requeue;
	}

	public void setRequeue(Boolean requeue) {
		this.requeue = requeue;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * {@inheritDoc}
	 */
	@JsonIgnore
	@Override
	public RequestParamsValidator getDefaultSearchParamsValidator() {
		return new RequestParamsValidator() {
			public void validate() throws ValidationException {
			}
		} ;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@JsonIgnore
	public void applyDefaultValues(boolean force) {
		if (force || sourceRoutingKey == null) {
			sourceRoutingKey = "#";
		}
		if (force || requeue == null) {
			requeue = false;
		}
		if (force || number == null || number < 1) {
			number = 1;
		}
	}

}
