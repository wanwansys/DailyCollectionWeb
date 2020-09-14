package com.shkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.Holiday;
import com.shkj.service.HolidayService;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
	@Autowired
	public HolidayService holidayService;
	
	@RequestMapping("/getHolidayList")
	@ResponseBody
	public PageInfo<Holiday> getHolidayList(int currentPage,int pageSize){	
		//设置分页信息，分别是当前页数和每页显示的总记录数
		PageHelper.startPage(currentPage, pageSize);
	    return holidayService.getHolidayList();
	}
	
	@RequestMapping("/insertHoliday")
	@ResponseBody
	public String insertHoliday(Holiday holiday){	
		//添加
		int result = holidayService.insertHoliday(holiday);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"添加成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"添加失败\"}";
		}
	}
	
	/*
	 * @RequestMapping("/updateHoliday")
	 * 
	 * @ResponseBody public String updateHoliday(Holiday holiday){ //修改 int result =
	 * holidayService.updateHoliday(holiday); if(result >= 1) { return
	 * "{\"result\":\"success\",\"msg\":\"修改负责人成功\"}"; } else { return
	 * "{\"result\":\"fail\",\"msg\":\"修改负责人失败\"}"; } }
	 */
	
	@RequestMapping("/deleteHoliday")
	@ResponseBody
	public String deleteHoliday(String day){	
		//删除
		int result = holidayService.deleteHoliday(day);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"删除成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"删除失败\"}";
		}
	}
}
