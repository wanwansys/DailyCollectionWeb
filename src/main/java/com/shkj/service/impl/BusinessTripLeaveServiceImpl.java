package com.shkj.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.BusinessTripLeave;
import com.shkj.mapper.BusinessTripLeaveMapper;
import com.shkj.service.BusinessTripLeaveService;
import org.springframework.stereotype.Service;

@Service
public class BusinessTripLeaveServiceImpl implements BusinessTripLeaveService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public BusinessTripLeaveMapper businessTripLeaveMapper;

	@Override
	public PageInfo<BusinessTripLeave> getBusinessTripLeaveList() {
		logger.info("查询出差及休假情况全部信息");
		List<BusinessTripLeave> list = businessTripLeaveMapper.getBusinessTripLeaveList();
		return new PageInfo<BusinessTripLeave>(list);
	}

	@Override
	public int insertBusinessTripLeave(BusinessTripLeave businessTripLeave) {
		logger.info("添加出差及休假情况");
		return businessTripLeaveMapper.insertBusinessTripLeave(businessTripLeave);
	}

	@Override
	public int updateBusinessTripLeave(BusinessTripLeave businessTripLeave) {
		logger.info("修改出差及休假情况");
		return businessTripLeaveMapper.updateBusinessTripLeave(businessTripLeave);
	}

	@Override
	public int deleteBusinessTripLeave(String id) {
		logger.info("删除出差及休假情况");
		return businessTripLeaveMapper.deleteBusinessTripLeave(id);
	}

	@Override
	public List<BusinessTripLeave> queryByDay(String dailyDay) {

		return businessTripLeaveMapper.queryByDaiyDay(dailyDay);
	}
}
