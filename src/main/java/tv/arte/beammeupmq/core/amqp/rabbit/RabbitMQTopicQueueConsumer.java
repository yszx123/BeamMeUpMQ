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


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Rabbit MQ topic queue consumer
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class RabbitMQTopicQueueConsumer extends DefaultRabbitMQueueConsumer {


	/**
	 * Constructor
	 */
	public RabbitMQTopicQueueConsumer() {
		super();
	}

	/**
	 * Constructor 
	 * 
	 * @param host Host name
	 * @param port Host connection port
	 * @param username Connection user name
	 * @param password Connection password
	 * @param virtualHost RabbitMQ virtual host
	 * @param exchangeName RabbitMQ exchange name
	 * @param routingKey Routing key
	 * @param queueName Queue name
	 */
	public RabbitMQTopicQueueConsumer(String host, int port, String username, String password, String virtualHost, String exchangeName, String routingKey, String queueName) {
		super();
		
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange(exchangeName);
		rabbitTemplate.setRoutingKey(routingKey);
		rabbitTemplate.setQueue(queueName);
		
		this.setConnectionFactory(connectionFactory);
		this.setRabbitTemplate(rabbitTemplate);
		this.setQueueName(queueName);
	}
}
