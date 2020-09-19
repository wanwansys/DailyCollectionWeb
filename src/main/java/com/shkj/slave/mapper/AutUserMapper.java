package com.shkj.slave.mapper;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.shkj.bean.AutUser;

@Repository
public interface AutUserMapper {
    //查询白名单全部信息
	public List<AutUser> getAutUserList();
}
