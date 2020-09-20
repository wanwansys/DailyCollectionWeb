package com.shkj.slave.service;

import com.github.pagehelper.PageInfo;

import com.shkj.bean.AutUser;

public interface AutUserService {
	public PageInfo<AutUser> getAutUserList();

    AutUser getUserById(String openId);
}
