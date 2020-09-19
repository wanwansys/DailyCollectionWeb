package com.shkj.service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.BusinessTripLeave;

import java.util.List;

public interface BusinessTripLeaveService {
    //查询
	public PageInfo<BusinessTripLeave> getBusinessTripLeaveList();
	//添加
	public int insertBusinessTripLeave(BusinessTripLeave businessTripLeave);
	//修改
	public int updateBusinessTripLeave(BusinessTripLeave businessTripLeave);
	//删除
	public int deleteBusinessTripLeave(String id);

    List<BusinessTripLeave> queryByDay(String dailyDay);
}
