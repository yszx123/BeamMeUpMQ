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


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;

import tv.arte.beammeupmq.core.MQConsumer;

/**
 * This is the default rabbit MQ queue consumer
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class DefaultRabbitMQueueConsumer extends AbstractBaseRabbitComunicationPoint implements MQConsumer<Message>{


	private static final String DEFAULT_ENCODING = "UTF-8";
	private volatile String encoding = DEFAULT_ENCODING;
	private volatile MessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
	
	private RabbitTemplate rabbitTemplate;
	private String queueName;
	
	/**
	 * Constructor
	 */
	public DefaultRabbitMQueueConsumer() {
		super();
	}

	/**
	 * Constructor 
	 * 
	 * @param connectionFactory A connection actory
	 * @param rabbitTemplate A rabbit tempalte
	 * @param queueName The queue name
	 */
	public DefaultRabbitMQueueConsumer(ConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate, String queueName) {
		super(connectionFactory);
		this.queueName = queueName;
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setQueue(queueName);
	}

	/**
	 * Set the rabbit template
	 * 
	 * @param rabbitTemplate The new rabbit template
	 */
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	/**
	 * Set the queue name
	 * 
	 * @param queueName The new queue name
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * {@inheritDoc}
	 */
	public Message consume() throws Exception {
		return this.rabbitTemplate.receive();
	}
	
	/**
	 * Consumes a given number of messages without acknowledge  them - they aren't took off the queue
	 * 
	 * @param number The number of messages to fetch
	 * @param shouldBeRequeued <code>true</code> if the messages should be re-queued (not acknowledges), <code>false</code> otherwise
	 * @return All fetched messages. 
	 * @throws Exception
	 */
	public List<Message> consumeMulti(final int number, Boolean shouldBeRequeued) throws Exception {
				
		if (shouldBeRequeued) {
			return this.rabbitTemplate.execute(new ChannelCallback<List<Message>>() {
				public List<Message> doInRabbit(Channel channel) throws IOException {
					List<Message> resultList = new ArrayList<Message>(number);
					GetResponse lastNonNullResponse = null;
					GetResponse response = null;
					for(int i=0; i<number; i++) {
						Message newMessage = null;
						
						response = channel.basicGet(queueName, false);
						// Response can be null is the case that there is no message on the queue.
						if (response != null) {
							newMessage =  DefaultRabbitMQueueConsumer.this.buildMessageFromResponse(response);
							lastNonNullResponse = response;
						}
						
						if (newMessage != null) {
							resultList.add(newMessage);
						} else {
							break;
						}
					}
					
					//Nack only the last message tag in MULTI mode
					if (lastNonNullResponse != null) {
						long deliveryTag = lastNonNullResponse.getEnvelope().getDeliveryTag();
						channel.basicNack(deliveryTag, true, true);
					}
					
					return resultList;
				}
			});
		} else {
			List<Message> resultList = new ArrayList<Message>(number);
			for(int i=0; i<number; i++) {
				Message newMessage = consume();
				if (newMessage != null) {
					resultList.add(newMessage);
				} else {
					break;
				}
			}
			return resultList;
		}
	}
	
	/**
	 * @see RabbitTemplate#buildMessageFromResponse(GetResponse)
	 */
	private Message buildMessageFromResponse(GetResponse response) {
		MessageProperties messageProps = this.messagePropertiesConverter.toMessageProperties(
				response.getProps(), response.getEnvelope(), this.encoding);
		messageProps.setMessageCount(response.getMessageCount());
		return new Message(response.getBody(), messageProps);
	}
}
