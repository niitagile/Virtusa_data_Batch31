package com.jsp.classes;

public class OverAllPosts {
	private int pId;
	private int uId;
	private String fileStore;
	private String tagLine;
	private String name;
	public OverAllPosts(int pId, int uId, String fileStore, String tagLine, String name) {
		super();
		this.pId = pId;
		this.uId = uId;
		this.fileStore = fileStore;
		this.tagLine = tagLine;
		this.name = name;
	}
	public OverAllPosts() {
		super();
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getFileStore() {
		return fileStore;
	}
	public void setFileStore(String fileStore) {
		this.fileStore = fileStore;
	}
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "OverAllPosts [pId=" + pId + ", uId=" + uId + ", fileStore=" + fileStore + ", tagLine=" + tagLine
				+ ", name=" + name + "]";
	}
	

}
