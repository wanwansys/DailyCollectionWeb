package com.shkj.mapper;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.shkj.bean.Holiday;

@Repository
public interface HolidayMapper {
	//查询节假日全部信息
	public List<Holiday> getHolidayList();
	//添加节假日
	public int insertHoliday(Holiday Holiday);
	//修改节假日
	public int updateHoliday(Holiday Holiday);
	//删除节假日
	public int deleteHoliday(String holiday);

    List<Holiday> queryByMonth(String yearmon);
}
