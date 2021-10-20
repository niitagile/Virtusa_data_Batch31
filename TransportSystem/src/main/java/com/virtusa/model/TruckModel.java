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
@Table(name="TruckModel")
public class TruckModel {
	@Id
	private int tid;
	private String modelName;
	private int numberOfTrucks;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getNumberOfTrucks() {
		return numberOfTrucks;
	}
	public void setNumberOfTrucks(int numberOfTrucks) {
		this.numberOfTrucks = numberOfTrucks;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTid() {
		return tid;
	}
	@Override
	public String toString() {
		return "TruckModel [tid=" + tid + ", modelName=" + modelName + ", numberOfTrucks=" + numberOfTrucks + "]";
	}
	
	
	
	
}
