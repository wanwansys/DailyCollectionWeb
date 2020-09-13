package com.shkj.bean;

public class WhiteUser {
	private String userNo;//用户编号
	private String userName;//用户姓名
	private String roleNo;//角色编号(0=普通用户 1=管理员)

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

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	@Override
	public String toString() {
		return "WhiteUser [userNo=" + userNo + ", userName=" + userName + ", roleNo=" + roleNo + "]";
	}	

}
