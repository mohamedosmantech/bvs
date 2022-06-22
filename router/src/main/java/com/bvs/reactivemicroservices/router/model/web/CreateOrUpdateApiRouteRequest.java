package com.bvs.reactivemicroservices.router.model.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrUpdateApiRouteRequest {


  @NotBlank
  private String path;

  private String method;

  @NotBlank
  private String consumer;
}
