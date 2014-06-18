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


import java.util.List;

import org.springframework.amqp.core.Message;

/**
 * A rabbit MQ utilities class
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public class RabbitMQUtils {

	/**
	 * Copy messages form one topic queue to another
	 * 
	 * @param rabbitMQRequestParams Rabbit connection configuration infos passed by a request
	 * @throws Exception
	 */
	public static void copyFromTopicQueueToTopicQueue(RabbitMQRequestParams rabbitMQRequestParams) throws Exception {
		RabbitMQTopicQueueConsumer consumer = new RabbitMQTopicQueueConsumer(
				rabbitMQRequestParams.getSourceHost(), rabbitMQRequestParams.getSourcePort()
				, rabbitMQRequestParams.getSourceUsername(), rabbitMQRequestParams.getSourcePassword()
				, rabbitMQRequestParams.getSourceVirtualHost(), rabbitMQRequestParams.getSourceExchange(),
				rabbitMQRequestParams.getSourceRoutingKey(), rabbitMQRequestParams.getSourceQueue()
		);
		
		RabbitMQTopicQueueProducer producer = new RabbitMQTopicQueueProducer(
				rabbitMQRequestParams.getDestinationHost(), rabbitMQRequestParams.getDestinationPort()
				, rabbitMQRequestParams.getDestinationUsername(), rabbitMQRequestParams.getDestinationPassword()
				, rabbitMQRequestParams.getDestinationVirtualHost(), rabbitMQRequestParams.getDestinationExchange()
				, rabbitMQRequestParams.getDestinationQueue()
		);
		
		List<Message> messages = consumer.consumeMulti(rabbitMQRequestParams.getNumber(), rabbitMQRequestParams.getRequeue());
		if (messages != null && ! messages.isEmpty()) {
			for(Message message : messages) {
				producer.produce(message);
			}
		}
	}
}
