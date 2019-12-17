package com.fronteo.cms.dto;

public class UserInfo {
	private String adminId;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public boolean isLogin(){
		return adminId==null || adminId.equals("")?false:true;
	}
}
