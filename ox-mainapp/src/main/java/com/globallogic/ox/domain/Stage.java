package com.globallogic.ox.domain;

import java.io.Serializable;
import java.util.List;

public class Stage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String type;
	private String url;
	private String branch;
	private Integer previous;
	private List<Integer> next;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Integer getPrevious() {
		return previous;
	}
	public void setPrevious(Integer previous) {
		this.previous = previous;
	}
	public List<Integer> getNext() {
		return next;
	}
	public void setNext(List<Integer> next) {
		this.next = next;
	}
	
	

}
