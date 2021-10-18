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
@Table(name = "multimedia")
public class Multimedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carouselid", unique = true, nullable = false)
	private int carouselid;
	
	@Column(name = "userid", unique = false, nullable = false)
	private int userId;
	
	@Column(name = "carouselupdate", unique = false, nullable = false)
	private String carouselupdate;
	
	@Column(name = "carouseltitle", unique = false, nullable = false)
	private String carouselTitle;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
    	private String carouselimage;

	public Multimedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Multimedia(int carouselid, int userId, String carouselupdate, String carouselTitle, String carouselimage) {
		super();
		this.carouselid = carouselid;
		this.userId = userId;
		this.carouselupdate = carouselupdate;
		this.carouselTitle = carouselTitle;
		this.carouselimage = carouselimage;
	}

	public int getCarouselid() {
		return carouselid;
	}

	public void setCarouselid(int carouselid) {
		this.carouselid = carouselid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCarouselupdate() {
		return carouselupdate;
	}

	public void setCarouselupdate(String carouselupdate) {
		this.carouselupdate = carouselupdate;
	}

	public String getCarouselTitle() {
		return carouselTitle;
	}

	public void setCarouselTitle(String carouselTitle) {
		this.carouselTitle = carouselTitle;
	}

	public String getCarouselimage() {
		return carouselimage;
	}

	public void setCarouselimage(String carouselimage) {
		this.carouselimage = carouselimage;
	}

	@Override
	public String toString() {
		return "Multimedia [carouselid=" + carouselid + ", userId=" + userId + ", carouselupdate=" + carouselupdate
				+ ", carouselTitle=" + carouselTitle + ", carouselimage=" + carouselimage + "]";
	}
	
	
}
