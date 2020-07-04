package com.example.messaging.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.messaging.model.Employee;

@Component
public class Receiver {

	@RabbitListener(queues = "${rabbitmq.queue}")
	public void recievedMessage(Employee employee) {
		System.out.println("*****Recieved Message From RabbitMQ: " + employee);
	}

}
