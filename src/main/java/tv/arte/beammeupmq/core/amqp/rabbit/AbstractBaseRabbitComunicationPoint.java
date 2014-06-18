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


import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * Base class for all 
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class AbstractBaseRabbitComunicationPoint {

	private ConnectionFactory connectionFactory;

	/**
	 * Constructor
	 */
	public AbstractBaseRabbitComunicationPoint() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param connectionFactory The connection factory to use
	 */
	public AbstractBaseRabbitComunicationPoint(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
		
	}

	/**
	 * Get the current connection factory
	 * 
	 * @return The connection factory
	 */
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * Set a new connection factory
	 * 
	 * @param connectionFactory The new connection factory
	 */
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
}
