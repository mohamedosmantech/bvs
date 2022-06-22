package com.bvs.reactivemicroservices.router.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.bvs.reactivemicroservices.router.service.GatewayRouteService;

@Service
public class GatewayRouteServiceImpl implements GatewayRouteService {
  
  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void refreshRoutes() {
    applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
  }
}
