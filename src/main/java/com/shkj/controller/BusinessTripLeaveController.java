package com.shkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.BusinessTripLeave;
import com.shkj.service.BusinessTripLeaveService;

@RestController
@RequestMapping("/businessTripLeave")
public class BusinessTripLeaveController {
	@Autowired
	public BusinessTripLeaveService businessTripLeaveService;
	
	@RequestMapping("/getBusinessTripLeaveList")
	@ResponseBody
	public PageInfo<BusinessTripLeave> getBusinessTripLeaveList(int currentPage,int pageSize){	
		//设置分页信息，分别是当前页数和每页显示的总记录数
		PageHelper.startPage(currentPage, pageSize);
	    return businessTripLeaveService.getBusinessTripLeaveList();
	}
	
	@RequestMapping("/insertBusinessTripLeave")
	@ResponseBody
	public String insertBusinessTripLeave(BusinessTripLeave businessTripLeave){	
		//添加
		int result = businessTripLeaveService.insertBusinessTripLeave(businessTripLeave);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"添加成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"添加失败\"}";
		}
	}
	
	@RequestMapping("/updateBusinessTripLeave")
	@ResponseBody
	public String updateBusinessTripLeave(BusinessTripLeave businessTripLeave){	
		//修改
		int result = businessTripLeaveService.updateBusinessTripLeave(businessTripLeave);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"修改成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"修改失败\"}";
		}
	}
	
	@RequestMapping("/deleteBusinessTripLeave")
	@ResponseBody
	public String deleteBusinessTripLeave(String id){	
		//删除
		int result = businessTripLeaveService.deleteBusinessTripLeave(id);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"删除成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"删除失败\"}";
		}
	}
}
