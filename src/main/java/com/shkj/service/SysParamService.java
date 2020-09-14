package com.shkj.service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.SysParam;

public interface SysParamService {
	//查询全部信息
	public PageInfo<SysParam> getSysParamList();
	//添加
	public int insertSysParam(SysParam sysParam);
	//修改
	public int updateSysParam(SysParam sysParam);
	//删除
	public int deleteSysParam(String paraType);
}
