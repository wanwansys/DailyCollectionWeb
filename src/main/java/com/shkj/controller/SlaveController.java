package com.shkj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.AutUser;
import com.shkj.slave.service.AutUserService;


@RestController
@RequestMapping("/slave")
public class SlaveController {
			
	@Autowired
	public AutUserService autUserService;
		
	@RequestMapping("/getAutUserList")
	@ResponseBody
	public PageInfo<AutUser> getAutUserList(int currentPage,int pageSize){	
		//设置分页信息，分别是当前页数和每页显示的总记录数
		PageHelper.startPage(currentPage, pageSize);
	    return autUserService.getAutUserList();
	}
}
