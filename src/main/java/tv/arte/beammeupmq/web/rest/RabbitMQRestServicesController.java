package tv.arte.beammeupmq.web.rest;

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


import java.util.ArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.arte.beammeupmq.core.BeamMeUpMQError;
import tv.arte.beammeupmq.core.BeamMeUpMQStandardResponse;
import tv.arte.beammeupmq.core.ValidationException;
import tv.arte.beammeupmq.core.amqp.rabbit.RabbitMQRequestParams;
import tv.arte.beammeupmq.core.amqp.rabbit.RabbitMQUtils;

/**
 * Controller responsible for serving RabbitMQ REST services
 * 
 * @author Simeon Petev
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/services/rabbitmq")
public class RabbitMQRestServicesController {
   
    private Logger logger = LoggerFactory.getLogger(RabbitMQRestServicesController.class);

    @RequestMapping(value = "/copy/topicq2q", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BeamMeUpMQStandardResponse copyTopicQueueToQueue(RabbitMQRequestParams rabbitMQRequestParams) {
        
    	BeamMeUpMQStandardResponse response = new BeamMeUpMQStandardResponse();
    	
    	try {
    		//Apply some default values for non provided
    		rabbitMQRequestParams.applyDefaultValues(false);
    		
    		//Validate parameters
			rabbitMQRequestParams.validate(true);
			
			//Copy messages
	    	try {
				RabbitMQUtils.copyFromTopicQueueToTopicQueue(rabbitMQRequestParams);
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.debug(ExceptionUtils.getFullStackTrace(e));
				response.setSuccess(false);
				response.addInfoMessage(null, e.getMessage());
			}
		} catch (ValidationException e1) {
			response.setSuccess(false);
			response.setErrorMessages(new ArrayList<BeamMeUpMQError>(e1.getValidationErrorMessages()));
		}
    	
        return response;
    }
}
