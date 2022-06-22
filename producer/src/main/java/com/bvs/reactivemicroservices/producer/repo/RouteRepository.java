package com.bvs.reactivemicroservices.producer.repo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.bvs.reactivemicroservices.producer.model.Route;
import reactor.core.publisher.Mono;

public interface RouteRepository extends ReactiveCrudRepository<Route, Integer> {
	@Query("SELECT consumer FROM routes r WHERE r.path = ? and r.method = ? limit 1")
	Mono<String> findByPathAndMethod(final String path, final String method);
}