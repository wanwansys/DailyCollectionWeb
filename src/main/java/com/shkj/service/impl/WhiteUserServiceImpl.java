package com.shkj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shkj.bean.WhiteUser;
import com.shkj.mapper.WhiteUserMapper;
import com.shkj.service.WhiteUserService;

@Service
public class WhiteUserServiceImpl implements WhiteUserService{

	@Autowired
	public WhiteUserMapper whiteUserMapper;
	
	@Override
	public WhiteUser getUser() {
		System.out.println("00000");
		return whiteUserMapper.getUser();
	}

}
