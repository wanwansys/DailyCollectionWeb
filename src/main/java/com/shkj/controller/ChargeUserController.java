package com.shkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.ChargeUser;
import com.shkj.service.ChargeUserService;

@RestController
@RequestMapping("/chargeUser")
public class ChargeUserController {

	@Autowired
	public ChargeUserService chargeUserService;
	
	@RequestMapping("/getChargeUserList")
	@ResponseBody
	public PageInfo<ChargeUser> getChargeUserList(int currentPage,int pageSize){	
		//设置分页信息，分别是当前页数和每页显示的总记录数
		PageHelper.startPage(currentPage, pageSize);
	    return chargeUserService.getChargeUserList();
	}
	
	@RequestMapping("/insertChargeUser")
	@ResponseBody
	public String insertChargeUser(ChargeUser chargeUser){	
		//添加负责人
		int result = chargeUserService.insertChargeUser(chargeUser);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"添加负责人成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"添加负责人失败\"}";
		}
	}
	
	@RequestMapping("/updateChargeUser")
	@ResponseBody
	public String updateChargeUser(ChargeUser chargeUser){	
		//修改负责人
		int result = chargeUserService.updateChargeUser(chargeUser);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"修改负责人成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"修改负责人失败\"}";
		}
	}
	
	@RequestMapping("/deleteChargeUser")
	@ResponseBody
	public String deleteChargeUser(String userNo){	
		//删除负责人
		int result = chargeUserService.deleteChargeUser(userNo);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"删除负责人成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"删除负责人失败\"}";
		}
	}
}
