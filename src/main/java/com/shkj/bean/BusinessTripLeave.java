package com.shkj.bean;

public class BusinessTripLeave {
	private String id;//id
	private String userNo;//用户编号
	private String type;//类型（0=出差 1=休假）
	private String startDate;//开始日期
	private String endDate;//结束日期
	private String comment;//备注
	private String userName;//用户姓名

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BusinessTripLeave [userNo=" + userNo + ", type=" + type + ", startDate=" + startDate + ", endDate="
				+ endDate + ", comment=" + comment + "]";
	}
	
	
}
