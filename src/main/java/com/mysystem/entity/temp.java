package com.mysystem.entity;

import java.io.Serializable;

public class temp implements Serializable{
	private static final long serialVersionUID = 1L; 
	private Integer uid;
	private String time;

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getUid() {
		return uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
