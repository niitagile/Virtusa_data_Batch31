package com.virtusa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BookedTrucks")

public class BookedTrucks {

	
	@Id
	private int tid;
	private String truckNum;
	private String bookedDate;
	private String truckModel;
	private String bookedBy;
	public String getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(String truckNum) {
		this.truckNum = truckNum;
	}
	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getTruckModel() {
		return truckModel;
	}
	public void setTruckModel(String truckModel) {
		this.truckModel = truckModel;
	}
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getBookedBy() {
		return bookedBy;
	}
	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
	@Override
	public String toString() {
		return "BookedTrucks [tid=" + tid + ", truckNum=" + truckNum + ", bookedDate=" + bookedDate + ", truckModel="
				+ truckModel + ", bookedBy=" + bookedBy + "]";
	}
	
	
}
