package com.fronteo.cms.dto;

public class UserInfo {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public boolean isLogin(){
		return userId==null || userId.equals("")?false:true;
	}
}
