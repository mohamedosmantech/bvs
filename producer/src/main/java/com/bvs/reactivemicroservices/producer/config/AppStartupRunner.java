package com.bvs.reactivemicroservices.producer.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bvs.reactivemicroservices.producer.model.Route;
import com.bvs.reactivemicroservices.producer.repo.RouteRepository;

import reactor.core.publisher.Flux;

@Component
public class AppStartupRunner implements ApplicationRunner {

	private static final String HEADER_PARAM = "consumer";

	@Autowired
	private HeadersExchange headerExchange;

	@Autowired
	private RouteRepository repository;

	@Autowired
	private AmqpAdmin admin;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Flux<Route> routesFlux = repository.findAll();
		routesFlux.subscribe(route -> {

			System.out.println(route.getConsumer());
			Queue queue = new Queue(route.getConsumer(), false);
			Binding binding = BindingBuilder.bind(queue).to(headerExchange).where(HEADER_PARAM)
					.matches(route.getConsumer());
			admin.declareQueue(queue);
			admin.declareBinding(binding);
		}

		);
	}
}
