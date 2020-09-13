package com.shkj.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.ChargeUser;
import com.shkj.mapper.ChargeUserMapper;
import com.shkj.service.ChargeUserService;

@Service
public class ChargeUserServiceImpl implements ChargeUserService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ChargeUserMapper chargeUserMapper;
	
	@Override
	public PageInfo<ChargeUser> getChargeUserList() {
		logger.info("查询负责人名单全部信息");
		List<ChargeUser> list = chargeUserMapper.getChargeUserList();
		return new PageInfo<ChargeUser>(list);
	}

	@Override
	public int insertChargeUser(ChargeUser chargeUser) {
		logger.info("添加负责人");
		return chargeUserMapper.insertChargeUser(chargeUser);
	}

	@Override
	public int updateChargeUser(ChargeUser chargeUser) {
		logger.info("修改负责人");
		return chargeUserMapper.updateChargeUser(chargeUser);
	}

	@Override
	public int deleteChargeUser(String userNo) {
		logger.info("删除负责人");
		return chargeUserMapper.deleteChargeUser(userNo);
	}

}
