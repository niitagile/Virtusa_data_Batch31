package com.springBoot.ConnectGlobe;

import java.io.FileInputStream;
import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class uploadEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pId;
	private int userId;
	private String tag;
	@Lob
	private byte[] image;
	
	
	public uploadEntity() {
		super();
	}

	public uploadEntity(int userId, String tag, byte[] image) {
		super();
		this.userId = userId;
		this.tag = tag;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getpId() {
		return pId;
	}

	@Override
	public String toString() {
		return "uploadEntity [pId=" + pId + ", userId=" + userId + ", tag=" + tag + ", image=" + Arrays.toString(image)
				+ "]";
	}
	
	
	
}
