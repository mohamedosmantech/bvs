package com.bvs.reactivemicroservices.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.reactivemicroservices.producer.model.Route;
import com.bvs.reactivemicroservices.producer.repo.RouteRepository;
import com.bvs.reactivemicroservices.producer.service.RabbitMQService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.springframework.http.HttpStatus;

@RestController
public class RoutesController {

	@Autowired
	private RabbitMQService service;

	@Autowired
	RouteRepository repository;

	Logger logger = LoggerFactory.getLogger(RoutesController.class);

	@PostMapping(value = "/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Route> save(@RequestBody Route route) {
		return repository.save(route).subscribeOn(Schedulers.boundedElastic());
	}

	@RequestMapping(value = "{path}")
	@ResponseStatus(HttpStatus.OK)
	public void producer(RequestEntity<Object> entity, @PathVariable("path") String path, ServerHttpRequest request) {
		logger.info("Router reciced a request to " + path);
		service.send(entity, "/" + path, request);
	}

}
