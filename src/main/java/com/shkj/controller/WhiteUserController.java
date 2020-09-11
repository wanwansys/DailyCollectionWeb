package com.shkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shkj.service.WhiteUserService;

@Controller
@RequestMapping("/whiteUser")
public class WhiteUserController {

	@Autowired
	public WhiteUserService whiteUserService;
	
	 @RequestMapping("/getUser")
	public void getUser() {
		System.out.println(whiteUserService.getUser().toString());
	}
}
