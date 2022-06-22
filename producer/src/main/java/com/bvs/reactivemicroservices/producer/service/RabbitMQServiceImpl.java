package com.bvs.reactivemicroservices.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import com.bvs.reactivemicroservices.producer.repo.RouteRepository;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private HeadersExchange headerExchange;

	@Autowired
	private RouteRepository repository;

	Logger logger = LoggerFactory.getLogger(RabbitMQServiceImpl.class);

	private static final String HEADER_PARAM = "consumer";

	public void send(RequestEntity<Object> entity, String path, ServerHttpRequest request) {

		logger.info("Finding consumer from My SQL DB .... method =" + entity.getMethod().name() + "  path =" + path);
		repository.findByPathAndMethod(path, entity.getMethod().name())
				.subscribe(consumer -> composeEvenet(entity, request, consumer)

				);

	}

	private void composeEvenet(RequestEntity<Object> entity, ServerHttpRequest request, String consumer) {
		logger.info("Composing event to consumer  ...." + consumer);
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader(HEADER_PARAM, consumer);
		messageProperties.setReplyToAddress(new Address(request.getRemoteAddress().getAddress().getHostAddress()));
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(entity.getBody(), messageProperties);
		amqpTemplate.send(headerExchange.getName(), "", message);
	}
}