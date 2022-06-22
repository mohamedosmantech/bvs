package com.bvs.reactivemicroservices.router.repository;



import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bvs.reactivemicroservices.router.model.database.ApiRoute;


public interface ApiRouteRepository extends ReactiveCrudRepository<ApiRoute, Long> {
  
}
