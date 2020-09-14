package com.shkj.mapper;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.shkj.bean.BusinessTripLeave;


@Repository
public interface BusinessTripLeaveMapper {
	//查询全部信息
	public List<BusinessTripLeave> getBusinessTripLeaveList();
	//添加
	public int insertBusinessTripLeave(BusinessTripLeave businessTripLeave);
	//修改
	public int updateBusinessTripLeave(BusinessTripLeave businessTripLeave);
	//删除
	public int deleteBusinessTripLeave(String id);
}
