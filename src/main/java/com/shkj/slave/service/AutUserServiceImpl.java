package com.shkj.slave.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.AutUser;
import com.shkj.slave.mapper.AutUserMapper;

@Service
public class AutUserServiceImpl implements AutUserService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public AutUserMapper autUserMapper;
	
	@Override
	public PageInfo<AutUser> getAutUserList() {	
		logger.info("查询白名单全部信息");
		List<AutUser> list = autUserMapper.getAutUserList();
		return new PageInfo<AutUser>(list);		
	}

	@Override
	public AutUser getUserById(String openId) {
		return autUserMapper.getAutUserById(openId);
	}


}
