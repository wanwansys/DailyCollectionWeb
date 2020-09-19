package com.shkj.controller;

import com.google.gson.JsonObject;
import com.shkj.bean.DailyMsgModel;
import com.shkj.bean.DailyReportInfo;
import com.shkj.service.DailyReportInfoService;
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

	@RequestMapping("/receiveDaily")
	@ResponseBody
	public JsonObject saveDaily(@RequestBody DailyMsgModel model) {
		logger.info("接收到招呼机器人消息转发内容：" + model.toString());
		DailyReportInfo info = new DailyReportInfo();
		info.setId(model.getMsgId());
		String openId = model.getFromId(); // 招呼openId
		// todo 根据openId 关联一事通用户Id
		info.setSpeakUserNo(model.getFromId());
		info.setSpeakInfo(model.getMsgContent());
		Date date = new Date(model.getTimestamp());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setSpeakTime(sd.format(date));

		JsonObject retMsg = new JsonObject();
		retMsg.addProperty("msgId", model.getMsgId());
		int code = infoService.saveDaily(info);
		if(code == 0) {
			retMsg.addProperty("code", 0);
			retMsg.addProperty("msg","接收成功");
		} else {
			retMsg.addProperty("code", 1);
			retMsg.addProperty("msg","接收失败");
		}
		// todo 插入发送记录表
		return retMsg;
	}

}
