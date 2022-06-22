package com.bvs.reactivemicroservices.router.service;

import com.bvs.reactivemicroservices.router.model.database.ApiRoute;
import com.bvs.reactivemicroservices.router.model.web.CreateOrUpdateApiRouteRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiRouteService {
  
  Flux<ApiRoute> findApiRoutes();
  
  Mono<ApiRoute> findApiRoute(Long id);
  
  Mono<Void> createApiRoute(CreateOrUpdateApiRouteRequest createOrUpdateApiRouteRequest);
  
  Mono<Void> updateApiRoute(Long id, CreateOrUpdateApiRouteRequest createOrUpdateApiRouteRequest);
  
  Mono<Void> deleteApiRoute(Long id);
}
