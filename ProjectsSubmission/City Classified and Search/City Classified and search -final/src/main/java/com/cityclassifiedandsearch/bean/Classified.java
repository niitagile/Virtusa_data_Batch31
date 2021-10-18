package com.cityclassifiedandsearch.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "classified")
public class Classified implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classifiedid", unique = true, nullable = false)
	private int classifiedId;
	
	@Column(name = "userid", unique = false, nullable = false)
	private int userId;
	
	@Column(name = "classifiedtitle", unique = false, nullable = false)
	private String classifiedTitle;
	
	@Column(name = "description", unique = false, nullable = true)
	private String description;
	
	@Column(name = "classifiedcategory", unique = false, nullable = false)
	private String classifiedCategory;
	
	@Column(name="approval",nullable=false)
	private boolean approval;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
    private String classifiedimage;

	public Classified() {
		super();
	}

	public Classified(int classifiedId, int userId, String classifiedTitle, String description,
			String classifiedCategory, boolean approval, String classifiedimage) {
		super();
		this.classifiedId = classifiedId;
		this.userId = userId;
		this.classifiedTitle = classifiedTitle;
		this.description = description;
		this.classifiedCategory = classifiedCategory;
		this.approval = approval;
		this.classifiedimage = classifiedimage;
	}

	public int getClassifiedId() {
		return classifiedId;
	}

	public void setClassifiedId(int classifiedId) {
		this.classifiedId = classifiedId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getClassifiedTitle() {
		return classifiedTitle;
	}

	public void setClassifiedTitle(String classifiedTitle) {
		this.classifiedTitle = classifiedTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassifiedCategory() {
		return classifiedCategory;
	}

	public void setClassifiedCategory(String classifiedCategory) {
		this.classifiedCategory = classifiedCategory;
	}
	
	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getClassifiedimage() {
		return classifiedimage;
	}

	public void setClassifiedimage(String classifiedimage) {
		this.classifiedimage = classifiedimage;
	}

	@Override
	public String toString() {
		return "Classified [classifiedId=" + classifiedId + ", userId=" + userId + ", classifiedTitle="
				+ classifiedTitle + ", description=" + description + ", classifiedCategory=" + classifiedCategory
				+ ", approval=" + approval + ", classifiedimage=" + classifiedimage + "]";
	}
}