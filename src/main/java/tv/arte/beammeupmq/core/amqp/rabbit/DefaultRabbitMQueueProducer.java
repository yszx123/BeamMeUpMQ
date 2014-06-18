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


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import tv.arte.beammeupmq.core.MQProducer;

/**
 * This is the default rabbit MQ queue producer (write)
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class DefaultRabbitMQueueProducer extends AbstractBaseRabbitComunicationPoint implements MQProducer<Message>{
	
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * Constructor
	 */
	public DefaultRabbitMQueueProducer() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param connectionFactory A connection factory
	 * @param rabbitTemplate A rabbit template
	 */
	public DefaultRabbitMQueueProducer(ConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate) {
		super(connectionFactory);
		this.rabbitTemplate = rabbitTemplate;
	}
	
	/**
	 * Set a new rabbitTemplate
	 * 
	 * @param rabbitTemplate The new rabbit template
	 */
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	/**
	 * {@inheritDoc}
	 */
	public void produce(Message message) throws Exception {
		rabbitTemplate.send(message);
	}	
}
