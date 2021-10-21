package com.springBoot.ConnectGlobe;

public class commentEntityClass {
	private int cId;
	private int pId;
	private int userId;
	private String comment;
	private String name;
	public commentEntityClass(int cId, int pId, int userId, String comment,String name) {
		super();
		this.cId = cId;
		this.pId = pId;
		this.userId = userId;
		this.comment = comment;
		this.name=name;
	}
	public commentEntityClass() {
		super();
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
