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
@Table(name="AvailableTrucks")

public class AvailableTrucks {

	@Id
	private int tid;
	private String truckNum;
	private String truckModel;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(String truckNum) {
		this.truckNum = truckNum;
	}
	public String getTruckModel() {
		return truckModel;
	}
	public void setTruckModel(String truckModel) {
		this.truckModel = truckModel;
	}
	
	@Override
	public String toString() {
		return "AvailableTrucks [tid=" + tid + ", truckNum=" + truckNum + ", truckModel=" + truckModel + "]";
	}
	
}