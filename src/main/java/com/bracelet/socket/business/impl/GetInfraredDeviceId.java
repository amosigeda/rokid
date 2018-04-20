package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.HongWai;
import com.bracelet.entity.HongWaiRegister;
import com.bracelet.service.IHongWaiKuService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.Utils;

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
	@Autowired
	IHongWaiKuService hongWaiKuService;

	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto,
			JSONObject jsonObject, Channel channel) {
		logger.info("===获取红外id：" + jsonObject.toJSONString());
		String imei = socketLoginDto.getImei();
		String hongWaiId = jsonObject.getString("id");
		HongWai hong = userInfoService.getHongWaiInfo(imei);

		if (hong != null) {
			if (!hongWaiId.equals(hong.getHongwai_id())) {
				userInfoService.updateHongWaiId(imei, hongWaiId);
			}
			HongWaiRegister res = hongWaiKuService
					.getHongWaiRegisterInfo(hongWaiId);
			if (res != null) {
				if (res.getRet_code() != 1) {
					String time = System.currentTimeMillis() / 1000 + "";
					String md5 = Utils.getmd5("none" + hongWaiId + time);
					String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
							+ md5.substring(7, 8) + md5.substring(15, 16)
							+ md5.substring(31, 32);
					String client = time + "_" + zuhe;
					String newMessage = "c=r&m=none&appid="
							+ Utils.HONGWAI_APPID + "&f=" + hongWaiId;
					String result = Utils.httpsRequest(Utils.HONGWAI_URL,
							Utils.REQUEST_POST, newMessage, client);

					hongWaiKuService.insertHongWaiRegisterInfolog(hongWaiId,
							"注册设备id", time, md5, zuhe, client, newMessage,
							result);

					JSONObject object = (JSONObject) JSON.parse(result);
					int retCode = object.getIntValue("ret_code");
					String retMsg = object.getString("ret_msg");

					hongWaiKuService.insertHongWaiRegisterInfo(hongWaiId,
							retCode, retMsg);
				}
			}
		} else {
			userInfoService.insertInfraredDeviceId(imei, hongWaiId);
			HongWaiRegister res = hongWaiKuService
					.getHongWaiRegisterInfo(hongWaiId);
			if (res != null) {
				if (res.getRet_code() != 1) {
					String time = System.currentTimeMillis() / 1000 + "";
					String md5 = Utils.getmd5("none" + hongWaiId + time);
					String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
							+ md5.substring(7, 8) + md5.substring(15, 16)
							+ md5.substring(31, 32);
					String client = time + "_" + zuhe;
					String newMessage = "c=r&m=none&appid="
							+ Utils.HONGWAI_APPID + "&f=" + hongWaiId;
					String result = Utils.httpsRequest(Utils.HONGWAI_URL,
							Utils.REQUEST_POST, newMessage, client);

					hongWaiKuService.insertHongWaiRegisterInfolog(hongWaiId,
							"注册设备id", time, md5, zuhe, client, newMessage,
							result);

					JSONObject object = (JSONObject) JSON.parse(result);
					int retCode = object.getIntValue("ret_code");
					String retMsg = object.getString("ret_msg");

					hongWaiKuService.insertHongWaiRegisterInfo(hongWaiId,
							retCode, retMsg);
				}
			}
		}

	/*	if (hong.getNum() == null || hong.getNum() == 0 || hong.getRs() == null
				|| "".equals(hong.getRs())) {

			String time = System.currentTimeMillis() / 1000 + "";
			String md5 = Utils.getmd5("none" + hongWaiId + time);
			String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
					+ md5.substring(7, 8) + md5.substring(15, 16)
					+ md5.substring(31, 32);
			String client = time + "_" + zuhe;
			String newMessage = "c=t&m=none&appid=" + Utils.HONGWAI_APPID
					+ "&f=" + hongWaiId;
			String result = Utils.httpsRequest(Utils.HONGWAI_URL,
					Utils.REQUEST_POST, newMessage, client);

			hongWaiKuService.insertHongWaiRegisterInfolog(hongWaiId,
					"获取被遥控设备类型列表", time, md5, zuhe, client, newMessage, result);

			JSONObject object = (JSONObject) JSON.parse(result);
			int sum = object.getIntValue("ret_code");
			String rs = object.getString("rs");

			userInfoService.updateHongWaiNumInfo(imei, sum, rs);
		}*/
		/*
		 * FingerDto sosDto = new FingerDto(); sosDto.setStatus(status);
		 * sosDto.setImei(imei); sosDto.setTimestamp(new Date().getTime());
		 * String target = tokenInfoService.getTokenByUserId(bind.getUser_id());
		 * String title = "红外参数设置"; String content = JSON.toJSONString(sosDto);
		 * 
		 * PushUtil.push(target, title, content, notifyContent); // save push
		 * log this.pushlogService.insert(bind.getUser_id(), imei, 0, target,
		 * title, content);
		 */

		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		return dto;
	}

}
