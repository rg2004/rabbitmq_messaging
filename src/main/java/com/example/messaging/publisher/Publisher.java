package com.example.messaging.publisher;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.messaging.MessagingApplication;
import com.example.messaging.model.Employee;
@Service
public class Publisher {
	
	 private static final Logger log = LoggerFactory.getLogger(Publisher.class);

	    private final RabbitTemplate rabbitTemplate;

	    public Publisher(final RabbitTemplate rabbitTemplate) {
	        this.rabbitTemplate = rabbitTemplate;
	    }

	    @Scheduled(fixedDelay = 30000L)
	    public void sendMessage() {
	    	
	    	int nextInt = new Random().nextInt(50);
	    	
	        final Employee message = new Employee("Employee"+nextInt,"EmpId"+nextInt);
	        log.info("Sending message...");
	        rabbitTemplate.convertAndSend(MessagingApplication.EXCHANGE_NAME, MessagingApplication.ROUTING_KEY, message);
	    }

}
