package com.bvs.reactivemicroservices.producer.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public interface RabbitMQService {

	void send(RequestEntity<Object> entity, String path, ServerHttpRequest request);

}