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


/**
 * Interface to be implemented by all writers to a message queue
 * Extend it on need.
 * 
 * @author Simeon Petev
 * @since 0.1
 */
public interface MQProducer<T> {
	
	/**
	 * Send (produces) a message to the exchange 
	 * 
	 * @param message The message to be sent
	 * @throws Exception In case of error
	 */
	public void produce(T message) throws Exception;
	
	/**
	 * Send (produces) a message to a queue
	 * 
	 * @param message The message to be sent
	 * @param queue The destination queue
	 * @throws Exception In case of error
	 */
	public void produce(T message, String queue) throws Exception;
}
