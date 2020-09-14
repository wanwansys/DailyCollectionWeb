package com.shkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.SysParam;
import com.shkj.service.SysParamService;

@RestController
@RequestMapping("/sysParams")
public class SysParamController {
	@Autowired
	public SysParamService sysParamService;
	
	@RequestMapping("/getSysParamList")
	@ResponseBody
	public PageInfo<SysParam> getSysParamList(int currentPage,int pageSize){	
		//设置分页信息，分别是当前页数和每页显示的总记录数
		PageHelper.startPage(currentPage, pageSize);
	    return sysParamService.getSysParamList();
	}
	
	@RequestMapping("/insertSysParam")
	@ResponseBody
	public String insertSysParam(SysParam sysParam){	
		//添加
		int result = sysParamService.insertSysParam(sysParam);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"添加成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"添加失败\"}";
		}
	}
	
	@RequestMapping("/updateSysParam")
	@ResponseBody
	public String updateSysParam(SysParam sysParam){	
		//修改
		int result = sysParamService.updateSysParam(sysParam);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"修改成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"修改失败\"}";
		}
	}
	
	@RequestMapping("/deleteSysParam")
	@ResponseBody
	public String deleteSysParam(String userNo){	
		//删除
		int result = sysParamService.deleteSysParam(userNo);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"删除成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"删除失败\"}";
		}
	}
}
