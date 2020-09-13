package com.shkj.mapper;

import java.util.List;
import java.util.Map;

import com.shkj.bean.DailyReportInfo;

public interface DailyReportInfoMapper {

	//查看全部人员日报
	public List<DailyReportInfo> getAllDailyReportInfoList(Map<String, String> map);
	//查看个人日报
	public List<DailyReportInfo> getPersonDailyReportInfoList(Map<String, String> map);
}
