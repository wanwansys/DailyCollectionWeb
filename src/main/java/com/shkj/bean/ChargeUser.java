package com.shkj.bean;

public class ChargeUser {
	private String userNo;//用户编号
	private String userName;//用户姓名
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "ChargeUser [userNo=" + userNo + ", userName=" + userName + "]";
	}
	
	
}
