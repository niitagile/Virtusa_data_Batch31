package com.jsp.classes;

import java.sql.Blob;

public class blobModel {
	private int pId;
	private int userId;
	private String fileStore;
	private String tagLine;
	
	public blobModel() {
		super();
	}
	public blobModel(int pId, int userId, String fileStore, String tagLine) {
		super();
		this.pId = pId;
		this.userId = userId;
		this.fileStore = fileStore;
		this.tagLine = tagLine;
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
	@Override
	public String toString() {
		return "blobModel [pId=" + pId + ", userId=" + userId + ", fileStore=" + fileStore + ", tagLine=" + tagLine
				+ "]";
	}
	
	
}
