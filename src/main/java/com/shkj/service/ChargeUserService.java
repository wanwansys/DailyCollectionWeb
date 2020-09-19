package com.shkj.service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.ChargeUser;

import java.util.List;

public interface ChargeUserService {
	//查询负责人名单全部信息
	public PageInfo<ChargeUser> getChargeUserList();
	//添加负责人
	public int insertChargeUser(ChargeUser chargeUser);
	//修改负责人
	public int updateChargeUser(ChargeUser chargeUser);
	//删除负责人
	public int deleteChargeUser(String userNo);

    List<ChargeUser> queryNosendDaily(String dailyDay);

	List<ChargeUser> queryNosendForDay(String dailyDay);
}
