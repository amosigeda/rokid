package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.FingerDto;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.BindDevice;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.IStepService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.business.IService;
import com.bracelet.util.PushUtil;

/**
 * 发送红外总数
 * 
 */
@Component("sendLableNum")
public class SendLableNum extends AbstractBizService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ITokenInfoService tokenInfoService;
	@Autowired
	IPushlogService pushlogService;

	@Autowired
	IUserInfoService userInfoService;
	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto,
			JSONObject jsonObject, Channel channel) {
		logger.info("===发送匹配型号总数：" + jsonObject.toJSONString());
		  String  imei = socketLoginDto.getImei();
		  Integer num = jsonObject.getInteger("num");
	
		  
		 userInfoService.updateHongWaiNumInfo(imei, num);
		 BindDevice bind=userInfoService.getBindInfoByImeiAndStatus(imei, 1);
		 
		FingerDto sosDto = new FingerDto();
		sosDto.setImei(imei);
		sosDto.setTimestamp(new Date().getTime());
		sosDto.setNum(num);
		String target = tokenInfoService.getTokenByUserId(bind.getUser_id());
		String title = "匹配型号总数";
		String content = JSON.toJSONString(sosDto);
		String notifyContent=title+"为"+num;
		PushUtil.push(target, title, content, notifyContent);
		// save push log
		this.pushlogService.insert(bind.getUser_id(), imei, 0, target, title, content);
		
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		return dto;
	}

}
