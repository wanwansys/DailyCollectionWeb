package com.shkj.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shkj.bean.WhiteUser;

@Repository
public interface WhiteUserMapper {
    //查询白名单全部信息
	public List<WhiteUser> getWhiteUserList();
	//添加白名单用户
	public int insertWhiteUser(WhiteUser whiteUser);
	//修改白名单用户
	public int updateWhiteUser(WhiteUser whiteUser);
	//删除白名单用户
	public int deleteWhiteUser(String userNo);
	//根据用户编号查询用户信息
	public WhiteUser getWhiteUser(String userNo);
}
