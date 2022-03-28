package edu.harvard.hms.dbmi.avillach.hpds.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.UUID;

/**
 * Information about interacting with a specific resource.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceInfo {
	private UUID id;
	private String name;
	private List<QueryFormat> queryFormats;
	public UUID getId() {
		return id;
	}
	public ResourceInfo setId(UUID id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ResourceInfo setName(String name) {
		this.name = name;
		return this;
	}
	public List<QueryFormat> getQueryFormats() {
		if (queryFormats == null) {
			queryFormats = new java.util.ArrayList<QueryFormat>();
		}
		return queryFormats;
	}
	public ResourceInfo setQueryFormats(List<QueryFormat> queryFormats) {
		this.queryFormats = queryFormats;
		return this;
	}
	
}
