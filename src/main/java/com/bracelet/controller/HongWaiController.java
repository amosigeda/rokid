package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.bracelet.dto.FingerDto;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.BindDevice;
import com.bracelet.entity.InfraredRequest;
import com.bracelet.entity.Location;
import com.bracelet.entity.LocationRequest;
import com.bracelet.entity.Step;
import com.bracelet.entity.UserInfo;
import com.bracelet.exception.BizException;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IStepService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.BaseChannelHandler;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.PushUtil;
import com.bracelet.util.RanomUtil;
import com.bracelet.util.RespCode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/infrared")
public class HongWaiController extends BaseController {

	@Autowired
	ILocationService locationService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IStepService stepService;
	@Resource
	BaseChannelHandler baseChannelHandler;
	private Logger logger = LoggerFactory.getLogger(getClass());

	

	@ResponseBody
	@RequestMapping(value = "/sendParameter", method = RequestMethod.POST)
	public HttpBaseDto get(@RequestParam String token,
			@RequestParam String imei, @RequestParam String type,
			@RequestParam String kind, @RequestParam String brand) {
		logger.info("发送红外参数=" + token);
		if (StringUtils.isAnyEmpty(token, imei, type, kind, brand)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);

		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		InfraredRequest re = new InfraredRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(32);
		re.setNo(RanomUtil.getFixLenthString(10));
		re.setBrand(brand);
		re.setKind(kind);
		re.setModel(type);

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(
					JSON.toJSONString(re) + "\r\n");
			logger.info("===request getLocation...ip:"
					+ socketLoginDto.getChannel().remoteAddress().toString()
					+ ",data:" + JSON.toJSONString(re));
		} else {
			logger.info("socketLoginDto error.no login.not active.token:"
					+ imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();

		return dto;
	}

}
