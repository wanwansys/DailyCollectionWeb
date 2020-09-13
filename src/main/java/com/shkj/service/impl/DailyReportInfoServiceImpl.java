package com.shkj.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shkj.bean.DailyReportInfo;
import com.shkj.mapper.DailyReportInfoMapper;
import com.shkj.service.DailyReportInfoService;

@Service
public class DailyReportInfoServiceImpl implements DailyReportInfoService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public DailyReportInfoMapper dailyReportInfoMapper;
	
	@Override
	public List<DailyReportInfo> getAllDailyReportInfoList(Map<String, String> map) {
		logger.info("查看全部人员日报");
		List<DailyReportInfo> list = dailyReportInfoMapper.getAllDailyReportInfoList(map);
		return list;
	}

	@Override
	public List<DailyReportInfo> getPersonDailyReportInfoList(Map<String, String> map) {
		logger.info("查看个人日报");
		List<DailyReportInfo> list = dailyReportInfoMapper.getPersonDailyReportInfoList(map);
		return list;
	}

}
