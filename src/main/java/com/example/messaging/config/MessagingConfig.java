package com.example.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

	@Value("${rabbitmq.exchange}")
	private String EXCHANGE_NAME;

	@Value("${rabbitmq.queue}")
	private String QUEUE_SPECIFIC_NAME;

	@Value("${rabbitmq.routing-key}")
	private String ROUTING_KEY;

	@Bean
	public TopicExchange appExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue appQueue() {
		return new Queue(QUEUE_SPECIFIC_NAME);
	}

	@Bean
	public Binding declareBinding() {
		return BindingBuilder.bind(appQueue()).to(appExchange()).with(ROUTING_KEY);
	}

	// You can comment the two methods below to use the default serialization /
	// deserialization (instead of JSON)
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
