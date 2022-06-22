package com.bvs.reactivemicroservices.producer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("routes")
public class Route {

	@Id
	@Column("id")
	private Long id;
	@Column("path")
	private String path;
	@Column("method")
	private String method;
	@Column("consumer")
	private String consumer;

	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Route(Long id, String path, String method, String consumer) {
		super();
		this.id = id;
		this.path = path;
		this.method = method;
		this.consumer = consumer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", path=" + path + ", method=" + method + ", consumer=" + consumer + "]";
	}

}
