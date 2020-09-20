package com.shkj.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.SysParam;
import com.shkj.mapper.SysParamMapper;
import com.shkj.service.SysParamService;

@Service
public class SysParamServiceImpl implements SysParamService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public SysParamMapper sysParamMapper;
	
	@Override
	public PageInfo<SysParam> getSysParamList() {
		logger.info("查询全部信息");
		List<SysParam> list = sysParamMapper.getSysParamList();
		return new PageInfo<SysParam>(list);
	}

	@Override
	public int insertSysParam(SysParam sysParam) {
		logger.info("添加");
		return sysParamMapper.insertSysParam(sysParam);
	}

	@Override
	public int updateSysParam(SysParam sysParam) {
		logger.info("修改");
		return sysParamMapper.updateSysParam(sysParam);
	}

	@Override
	public int deleteSysParam(String paraType) {
		logger.info("删除");
		return sysParamMapper.deleteSysParam(paraType);
	}

	@Override
	public String getValueBykey(String paraType) {
		return sysParamMapper.getValueBykey(paraType);
	}

}
