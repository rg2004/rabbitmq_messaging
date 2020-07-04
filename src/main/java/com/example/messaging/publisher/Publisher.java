package com.example.messaging.publisher;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.messaging.model.Employee;

@Service
public class Publisher {

	private static final Logger log = LoggerFactory.getLogger(Publisher.class);

	@Value("${rabbitmq.exchange}")
	private String EXCHANGE_NAME;

	@Value("${rabbitmq.queue}")
	private String QUEUE_SPECIFIC_NAME;

	@Value("${rabbitmq.routing-key}")
	private String ROUTING_KEY;

	private final RabbitTemplate rabbitTemplate;

	public Publisher(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedDelay = 30000L)
	public void sendMessage() {

		int nextInt = new Random().nextInt(50);

		final Employee message = new Employee("Employee" + nextInt, "EmpId" + nextInt);
		log.info("Sending message...");
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
	}

}
