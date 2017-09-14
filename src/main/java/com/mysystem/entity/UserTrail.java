package com.mysystem.entity;

public class UserTrail {
	private Integer utid;//账户申请id
	private String username;//用户名
	private String password;//密码
	private String tradePass; 
	public String getTradePass() {
		return tradePass;
	}
	public void setTradePass(String tradePass) {
		this.tradePass = tradePass;
	}
	private String email;//邮箱
	private Integer age;//年龄
	private String position;//职位
	private Integer ustatus;//账户申请状态
	public Integer getUtid() {
		return utid;
	}
	public void setUtid(Integer utid) {
		this.utid = utid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getUstatus() {
		return ustatus;
	}
	public void setUstatus(Integer ustatus) {
		this.ustatus = ustatus;
	}
	
	
}
