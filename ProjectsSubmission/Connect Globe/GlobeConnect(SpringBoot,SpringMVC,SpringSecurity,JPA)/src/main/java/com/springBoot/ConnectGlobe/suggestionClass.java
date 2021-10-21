package com.springBoot.ConnectGlobe;

public class suggestionClass {

	private int rId;
	private String suggest;
	private String name;
	public suggestionClass(int rId, String suggest, String name) {
		super();
		this.rId = rId;
		this.suggest = suggest;
		this.name = name;
	}
	public suggestionClass() {
		super();
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
