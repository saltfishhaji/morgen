package com.mysystem.entity;
import java.io.Serializable;
public class User implements Serializable{
	private Integer uid;//�û�id
	private Integer utid;//�˻�����id
	private double point;//�˻�����
	private Integer priority;//�˻�Ȩ��
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getUtid() {
		return utid;
	}
	public void setUtid(Integer utid) {
		this.utid = utid;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	

}
