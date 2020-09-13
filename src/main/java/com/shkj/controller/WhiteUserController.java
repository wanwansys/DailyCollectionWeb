package com.shkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.WhiteUser;
import com.shkj.service.WhiteUserService;

@RestController
@RequestMapping("/whiteUser")
public class WhiteUserController {

	@Autowired
	public WhiteUserService whiteUserService;
			
	@RequestMapping("/getWhiteUserList")
	@ResponseBody
	public PageInfo<WhiteUser> getWhiteUserList(int currentPage,int pageSize){	
		//设置分页信息，分别是当前页数和每页显示的总记录数
		PageHelper.startPage(currentPage, pageSize);
	    return whiteUserService.getWhiteUserList();
	}
	
	@RequestMapping("/insertWhiteUser")
	@ResponseBody
	public String insertWhiteUser(WhiteUser whiteUser){	
		//添加白名单用户
		int result = whiteUserService.insertWhiteUser(whiteUser);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"添加白名单用户成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"添加白名单用户失败\"}";
		}
	}
	
	@RequestMapping("/updateWhiteUser")
	@ResponseBody
	public String updateWhiteUser(WhiteUser whiteUser){	
		//修改白名单用户
		int result = whiteUserService.updateWhiteUser(whiteUser);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"修改白名单用户成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"修改白名单用户失败\"}";
		}
	}
	
	@RequestMapping("/deleteWhiteUser")
	@ResponseBody
	public String deleteWhiteUser(String userNo){	
		//删除白名单用户
		int result = whiteUserService.deleteWhiteUser(userNo);
		if(result >= 1) {
			return "{\"result\":\"success\",\"msg\":\"删除白名单用户成功\"}";
		} else {
			return "{\"result\":\"fail\",\"msg\":\"删除白名单用户失败\"}";
		}
	}
}
