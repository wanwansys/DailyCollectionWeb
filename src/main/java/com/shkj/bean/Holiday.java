package com.shkj.bean;

public class Holiday {
	private String day;//日期
	private String type;//0-假日 1=补班
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Holiday [day=" + day + ", type=" + type + "]";
	}
	
	
	
}
