package com.springBoot.ConnectGlobe;


public class AuthenticationResponse {
	private String jwt;
	private UserModel pModel;
	private CredentialModel model;
	private String msg;
	
	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String jwt,UserModel pModel, CredentialModel model, String msg) {
		super();
		this.jwt = jwt;
		this.pModel=pModel;
		this.model = model;
		this.msg = msg;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public CredentialModel getModel() {
		return model;
	}

	public void setModel(CredentialModel model) {
		this.model = model;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserModel getpModel() {
		return pModel;
	}

	public void setpModel(UserModel pModel) {
		this.pModel = pModel;
	}
	

}
