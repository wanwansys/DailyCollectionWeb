package com.shkj.service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.WhiteUser;

public interface WhiteUserService {
	//查询白名单全部信息
	public PageInfo<WhiteUser> getWhiteUserList();
	//添加白名单用户
	public int insertWhiteUser(WhiteUser whiteUser);
	//修改白名单用户
	public int updateWhiteUser(WhiteUser whiteUser);
	//删除白名单用户
	public int deleteWhiteUser(String userNo);
}
