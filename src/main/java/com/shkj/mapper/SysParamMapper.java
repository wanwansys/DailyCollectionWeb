package com.shkj.mapper;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.shkj.bean.SysParam;

@Repository
public interface SysParamMapper {
	//查询全部信息
	public List<SysParam> getSysParamList();
	//添加
	public int insertSysParam(SysParam sysParam);
	//修改
	public int updateSysParam(SysParam sysParam);
	//删除
	public int deleteSysParam(String paraType);
}
