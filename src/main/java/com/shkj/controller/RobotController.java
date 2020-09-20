package com.shkj.controller;

import com.alibaba.fastjson.JSONObject;

import com.shkj.bean.AutUser;
import com.shkj.bean.DailyMsgModel;
import com.shkj.bean.DailyReportInfo;
import com.shkj.service.DailyReportInfoService;
import com.shkj.slave.service.AutUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对接招呼机器人，用于接收转发的日报信息
 */
@RestController
@RequestMapping("/robotInterface")
public class RobotController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public DailyReportInfoService infoService;
	@Autowired
	public AutUserService autUserService;


	@RequestMapping("/receiveDaily")
	@ResponseBody
	public JSONObject saveDaily(@RequestBody DailyMsgModel model) {
		logger.info("【接收到招呼机器人消息转发内容】：" + model.toString());
		DailyReportInfo info = new DailyReportInfo();
		info.setId(model.getMsgId());
		String openId = model.getFromId(); // 招呼openId
		// todo 根据openId 关联一事通用户Id
		AutUser autUser = autUserService.getUserById(openId);
		info.setSpeakUserNo(autUser.getUser_id());
		System.out.println(autUser.getUser_name());
		info.setSpeakUserName(autUser.getUser_name());
		info.setSpeakInfo(model.getMsgContent());
		Date date = new Date(model.getTimestamp());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setSpeakTime(sd.format(date));

		JSONObject posJson = new JSONObject();
		posJson.put("msgId", model.getMsgId());
		int code = infoService.saveDaily(info);
		if(code == 0) {
			posJson.put("code", "0");
			posJson.put("msg", "接收成功");
		} else {
			posJson.put("code", "1");
			posJson.put("msg", "接收失败");
		}
		// todo 插入发送记录表
		return posJson;
	}

}