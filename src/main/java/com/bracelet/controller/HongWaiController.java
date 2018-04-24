package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.bracelet.service.IHongWaiKuService;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IStepService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.BaseChannelHandler;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.GZIPUtils;
import com.bracelet.util.PushUtil;
import com.bracelet.util.RanomUtil;
import com.bracelet.util.RespCode;
import com.bracelet.util.Utils;

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
	@Autowired
	IHongWaiKuService hongWaiKuService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * 发送红外码库
	 */
	@ResponseBody
	@RequestMapping(value = "/sendParameter", method = RequestMethod.POST)
	public HttpBaseDto get(@RequestParam String token,
			@RequestParam String imei, @RequestParam String r,
			@RequestParam String f) {
		logger.info("发送红外码库=" + token);
		if (StringUtils.isAnyEmpty(token, imei)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);

		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		String time = System.currentTimeMillis() / 1000 + "";
		String md5 = Utils.getmd5("none" + r + f + time);
		String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
				+ md5.substring(7, 8) + md5.substring(15, 16)
				+ md5.substring(31, 32);
		String client = time + "_" + zuhe;
		String newMessage = "c=d&m=none&r=" + r + "&appid="
				+ Utils.HONGWAI_APPID + "&f=" + f;
		String result = Utils.httpsRequest(Utils.HONGWAI_URL,
				Utils.REQUEST_POST, newMessage, client);

		hongWaiKuService.insertHongWaiRegisterInfolog(f, "根据品牌ID、设备类型获取遥控器列表",
				time, md5, zuhe, client, newMessage, result);
		byte[] b = GZIPUtils.compress(result);
		int size = b.length;

		InfraredRequest re = new InfraredRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(101);
		re.setNo(RanomUtil.getFixLenthString(10));
		re.setSize(size);
		re.setB(b);
		re.setResult(result);

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
	
	
	/*
	 * 得到遥控设备类型列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getDeviceTypeList", method = RequestMethod.POST)
	public HttpBaseDto getDeviceTypeList(@RequestParam String token,@RequestParam String f) {
		logger.info("获取被遥控设备类型列表=" + token);
		if (StringUtils.isAnyEmpty(token, f)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);

		String time = System.currentTimeMillis() / 1000 + "";
		String md5 = Utils.getmd5("none"  + f + time);
		String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
				+ md5.substring(7, 8) + md5.substring(15, 16)
				+ md5.substring(31, 32);
		String client = time + "_" + zuhe;
		String newMessage = "c=t&m=none&appid="
				+ Utils.HONGWAI_APPID + "&f=" + f;
		String result = Utils.httpsRequest(Utils.HONGWAI_URL,
				Utils.REQUEST_POST, newMessage, client);

		hongWaiKuService.insertHongWaiRegisterInfolog(f, "获取被遥控设备类型列表",
				time, md5, zuhe, client, newMessage, result);
		Map<String, Object> pwd = new HashMap<>();
		pwd.put("result", result);

		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(pwd);
		return dto;
	}
	
	
	/*
	 * 得到遥控品牌设备类型列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getDeviceBrandList", method = RequestMethod.POST)
	public HttpBaseDto getDeviceBrandList(@RequestParam String token,@RequestParam String t,@RequestParam String f) {
		logger.info("得到遥控品牌设备类型列表=" + token);
		if (StringUtils.isAnyEmpty(token, f)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);

		String time = System.currentTimeMillis() / 1000 + "";
		String md5 = Utils.getmd5("none" + t + f + time);
		String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
				+ md5.substring(7, 8) + md5.substring(15, 16)
				+ md5.substring(31, 32);
		String client = time + "_" + zuhe;
		String newMessage = "c=f&m=none&t="+t+"&appid="
				+ Utils.HONGWAI_APPID + "&f=" + f;
		String result = Utils.httpsRequest(Utils.HONGWAI_URL,
				Utils.REQUEST_POST, newMessage, client);

		hongWaiKuService.insertHongWaiRegisterInfolog(f, "得到遥控品牌设备类型列表",
				time, md5, zuhe, client, newMessage, result);
		Map<String, Object> pwd = new HashMap<>();
		pwd.put("result", result);

		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(pwd);
		return dto;
	}
	

}
