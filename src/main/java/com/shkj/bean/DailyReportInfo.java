package com.shkj.bean;

public class DailyReportInfo {
	private String id;//id
	private String speakUserNo;//发言人编号
	private String speakUserName;//发言人
	private String speakDeptNo;//部门编号
	private String speakDeptName;//部门名称
	private String speakInfo;//发言内容
	private String speakTime;//发言时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpeakUserNo() {
		return speakUserNo;
	}
	public void setSpeakUserNo(String speakUserNo) {
		this.speakUserNo = speakUserNo;
	}
	public String getSpeakUserName() {
		return speakUserName;
	}
	public void setSpeakUserName(String speakUserName) {
		this.speakUserName = speakUserName;
	}
	public String getSpeakDeptNo() {
		return speakDeptNo;
	}
	public void setSpeakDeptNo(String speakDeptNo) {
		this.speakDeptNo = speakDeptNo;
	}
	public String getSpeakDeptName() {
		return speakDeptName;
	}
	public void setSpeakDeptName(String speakDeptName) {
		this.speakDeptName = speakDeptName;
	}
	public String getSpeakInfo() {
		return speakInfo;
	}
	public void setSpeakInfo(String speakInfo) {
		this.speakInfo = speakInfo;
	}
	public String getSpeakTime() {
		return speakTime;
	}
	public void setSpeakTime(String speakTime) {
		this.speakTime = speakTime;
	}
	
	@Override
	public String toString() {
		return "DailyReportInfo [id=" + id + ", speakUserNo=" + speakUserNo + ", speakUserName=" + speakUserName
				+ ", speakDeptNo=" + speakDeptNo + ", speakDeptName=" + speakDeptName + ", speakInfo=" + speakInfo
				+ ", speakTime=" + speakTime + "]";
	}
	
	

}
