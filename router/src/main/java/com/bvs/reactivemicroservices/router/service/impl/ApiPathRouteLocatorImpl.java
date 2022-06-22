package com.bvs.reactivemicroservices.router.service.impl;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.util.StringUtils;

import com.bvs.reactivemicroservices.router.model.database.ApiRoute;
import com.bvs.reactivemicroservices.router.service.ApiRouteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ApiPathRouteLocatorImpl implements RouteLocator {

	private final ApiRouteService apiRouteService;

	private final RouteLocatorBuilder routeLocatorBuilder;

	@Override
	public Flux<Route> getRoutes() {
		RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
//		System.out.println(routesBuilder.toString());
//		Mono<List<Builder>> x =apiRouteService.findApiRoutes()
//				.map(
//						apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getConsumer()),
//						predicateSpec -> setPredicateSpec(apiRoute, predicateSpec))
//						)
//				.collectList();
//		System.out.println(x.toFuture().toString());
		return apiRouteService.findApiRoutes()
				.map(
						apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getConsumer()),
						predicateSpec -> setPredicateSpec(apiRoute, predicateSpec))
						)
				.collectList().flatMapMany(builders -> {
				return routesBuilder.build().getRoutes();
				
				});
	}

	private Buildable<Route> setPredicateSpec(ApiRoute apiRoute, PredicateSpec predicateSpec) {
//		System.out.println(predicateSpec.toString());
		return predicateSpec.path(apiRoute.getPath()).and().method(apiRoute.getMethod())
//				.filters(
//				f -> f.addRequestParameter("sender-info", "").addRequestHeader("consumer-marker", apiRoute.getConsumer())
//				)
		.uri(apiRoute.getConsumer());
	}
}
