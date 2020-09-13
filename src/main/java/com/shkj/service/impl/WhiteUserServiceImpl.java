package com.shkj.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.WhiteUser;
import com.shkj.mapper.WhiteUserMapper;
import com.shkj.service.WhiteUserService;

@Service
public class WhiteUserServiceImpl implements WhiteUserService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public WhiteUserMapper whiteUserMapper;
	
	@Override
	public PageInfo<WhiteUser> getWhiteUserList() {	
		logger.info("查询白名单全部信息");
		List<WhiteUser> list = whiteUserMapper.getWhiteUserList();
		return new PageInfo<WhiteUser>(list);		
	}


	@Override
	public int insertWhiteUser(WhiteUser whiteUser) {
		logger.info("添加白名单用户");
		return whiteUserMapper.insertWhiteUser(whiteUser);
	}


	@Override
	public int updateWhiteUser(WhiteUser whiteUser) {
		logger.info("修改白名单用户");
		return whiteUserMapper.updateWhiteUser(whiteUser);
	}


	@Override
	public int deleteWhiteUser(String userNo) {
		logger.info("删除白名单用户");
		return whiteUserMapper.deleteWhiteUser(userNo);
	}

}
