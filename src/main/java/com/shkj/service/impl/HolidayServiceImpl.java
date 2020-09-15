package com.shkj.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.Holiday;
import com.shkj.mapper.HolidayMapper;
import com.shkj.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public HolidayMapper holidayMapper;
	
	@Override
	public PageInfo<Holiday> getHolidayList() {
		logger.info("查询全部信息");
		List<Holiday> list = holidayMapper.getHolidayList();
		return new PageInfo<Holiday>(list);
	}

	@Override
	public int insertHoliday(Holiday holiday) {
		logger.info("添加");
		return holidayMapper.insertHoliday(holiday);
	}

	
	@Override public int updateHoliday(Holiday holiday) { 
		logger.info("修改");
	    return holidayMapper.updateHoliday(holiday); 
	 }
	

	@Override
	public int deleteHoliday(String day) {
		logger.info("删除");
		return holidayMapper.deleteHoliday(day);
	}

}
