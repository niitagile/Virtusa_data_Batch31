package com.springBoot.ConnectGlobe;

public class imageEntityClass {
	private int pId;
	private int userId;
	private String tag;
	private String image;
	private String name;
	public imageEntityClass(int pId, int userId, String tag, String image,String name) {
		super();
		this.pId = pId;
		this.userId = userId;
		this.tag = tag;
		this.image = image;
		this.name=name;
	}
	public imageEntityClass() {
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "imageEntityClass [pId=" + pId + ", userId=" + userId + ", tag=" + tag + ", image=" + image + ", name="
				+ name + "]";
	}
	

}
