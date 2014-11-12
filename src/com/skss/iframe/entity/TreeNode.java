package com.skss.iframe.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有树实体类
 * @author guozhgang
 *
 */
@SuppressWarnings("rawtypes")
public class TreeNode {
	public TreeNode() {}
	private String id;
	private String text;
	private String state;
	private List children = new ArrayList();
	private Map<String, Object> attributes = new HashMap<String, Object>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	
	
}
