package com.shkj.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shkj.bean.ChargeUser;

@Repository
public interface ChargeUserMapper {
	//查询负责人名单全部信息
	public List<ChargeUser> getChargeUserList();
	//添加负责人
	public int insertChargeUser(ChargeUser chargeUser);
	//修改负责人
	public int updateChargeUser(ChargeUser chargeUser);
	//删除负责人
	public int deleteChargeUser(String userNo);
}
