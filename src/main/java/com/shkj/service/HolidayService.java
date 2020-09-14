package com.shkj.service;

import com.github.pagehelper.PageInfo;
import com.shkj.bean.Holiday;

public interface HolidayService {
	//查询全部信息
	public PageInfo<Holiday> getHolidayList();
	//添加
	public int insertHoliday(Holiday holiday);
	//修改
	//public int updateHoliday(Holiday holiday);
	//删除
	public int deleteHoliday(String day);
}
