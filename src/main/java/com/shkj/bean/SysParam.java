package com.shkj.bean;

public class SysParam {
	private String paraType;//参数类型
	private String paraValue;//参数值
	private String paraDesc;//参数用途及说明
	
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getParaDesc() {
		return paraDesc;
	}
	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}
	@Override
	public String toString() {
		return "SysParam [paraType=" + paraType + ", paraValue=" + paraValue + ", paraDesc=" + paraDesc + "]";
	}
	
	
}
