package com.skss.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.skss.iframe.entity.IdEntity;

@Entity
@Table(name = "ss_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SsMenu extends IdEntity{
	private String id;
	private String description;
	private String nodeId;
	private String parent;
	private String text;
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "nodeId")
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	@Column(name = "parent")
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Column(name = "text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
