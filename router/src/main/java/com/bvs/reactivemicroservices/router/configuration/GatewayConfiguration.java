package com.bvs.reactivemicroservices.router.configuration;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bvs.reactivemicroservices.router.service.ApiRouteService;
import com.bvs.reactivemicroservices.router.service.impl.ApiPathRouteLocatorImpl;

@Configuration
public class GatewayConfiguration {
  
  @Bean
  public RouteLocator routeLocator(ApiRouteService apiRouteService,
      RouteLocatorBuilder routeLocatorBuilder) {
    return new ApiPathRouteLocatorImpl(apiRouteService, routeLocatorBuilder);
  }
}
