package com.bvs.reactivemicroservices.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ReplyService {

	Logger logger = LoggerFactory.getLogger(ReplyService.class);

	public Mono<String> reply(Message message) {
		logger.info("Consumer Replying to client");

		MessageProperties mesgProps = message.getMessageProperties();
		String clientUri = mesgProps.getReplyTo();
		WebClient client = WebClient.create();

		return client.post().uri(clientUri).body(Mono.just(message.getBody()), getClass()).retrieve()
				.bodyToMono(String.class);

	}
}
