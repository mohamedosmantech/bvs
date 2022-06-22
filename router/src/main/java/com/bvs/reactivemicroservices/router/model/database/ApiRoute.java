package com.bvs.reactivemicroservices.router.model.database;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.relational.core.mapping.Table;
import com.bvs.reactivemicroservices.router.model.constant.TableName;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(TableName.API_ROUTE)
public class ApiRoute {
  
 
  private Long id;
  
  private String path;
  private String method;
  private String consumer;
}
