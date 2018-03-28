package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.IStepService;
import com.bracelet.socket.business.IService;

/**
 * 关灯服务
 * 
 */
@Component("closeLightService")
public class CloseLightService extends AbstractBizService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto,
			JSONObject jsonObject, Channel channel) {
		logger.info("===关灯-返回：" + jsonObject.toJSONString());

		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		return dto;
	}

}
