package com.bvs.reactivemicroservices.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.scheduler.Schedulers;

@Service
public class RabbitMQListner implements MessageListener {

	@Autowired
	private ReplyService replyService;

	Logger logger = LoggerFactory.getLogger(RabbitMQListner.class);

	public void onMessage(Message message) {
		logger.info("Consuming Message - " + message.getMessageProperties());
		if (replyService == null)
			replyService = new ReplyService();

		replyService.reply(message).subscribeOn(Schedulers.boundedElastic());

	}
}