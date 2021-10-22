package com.springBoot.ConnectGlobe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class commentEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cId;
	private int pId;
	private int userId;
	private String comment;
	public commentEntity(int pId, int userId, String comment) {
		super();
		this.pId = pId;
		this.userId = userId;
		this.comment = comment;
	}
	public commentEntity() {
		super();
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
	public int getcId() {
		return cId;
	}
	
}
