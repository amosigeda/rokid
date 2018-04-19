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
import com.bracelet.entity.HongWai;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.IStepService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.business.IService;
import com.bracelet.util.PushUtil;

/**
 * 获取红外id
 * 
 */
@Component("getInfraredDeviceIdService")
public class GetInfraredDeviceId extends AbstractBizService {

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
		logger.info("===获取红外id：" + jsonObject.toJSONString());
		String imei = socketLoginDto.getImei();
		String hongWaiId = jsonObject.getString("id");
		HongWai hong = userInfoService.getHongWaiInfo(imei);
		if(hong != null){
			userInfoService.updateHongWaiId(imei,hongWaiId);
		}else{
			userInfoService.insertInfraredDeviceId(imei,hongWaiId);
		}

/*		FingerDto sosDto = new FingerDto();
		sosDto.setStatus(status);
		sosDto.setImei(imei);
		sosDto.setTimestamp(new Date().getTime());
		String target = tokenInfoService.getTokenByUserId(bind.getUser_id());
		String title = "红外参数设置";
		String content = JSON.toJSONString(sosDto);

		PushUtil.push(target, title, content, notifyContent);
		// save push log
		this.pushlogService.insert(bind.getUser_id(), imei, 0, target, title,
				content);*/

		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		return dto;
	}
	public static void main(String[] args) {
		System.out.println(1);
	}

}
