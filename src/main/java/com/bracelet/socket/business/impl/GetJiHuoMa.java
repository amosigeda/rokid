package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.bracelet.entity.HongWaiJiHuo;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.IHongWaiKuService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.IStepService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.business.IService;
import com.bracelet.util.PushUtil;
import com.bracelet.util.Utils;

/**
 * 获取激活码
 * 
 */
@Component("getJiHuoMa")
public class GetJiHuoMa extends AbstractBizService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IHongWaiKuService hongWaiKuService;

	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto,
			JSONObject jsonObject, Channel channel) {
		logger.info("===获取激活码：" + jsonObject.toJSONString());
		String hongWaiId = jsonObject.getString("f");
		String imei = socketLoginDto.getImei();
		Map<String, Object> pwd = new HashMap<>();
		HongWaiJiHuo jihuo = hongWaiKuService
				.getHongWaiJiHuoInfoByImeiAndDeviceId(imei, hongWaiId);

		if (jihuo == null) {
			String time = System.currentTimeMillis() / 1000 + "";
			String md5 = Utils.getmd5("none" + hongWaiId + time);
			String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
					+ md5.substring(7, 8) + md5.substring(15, 16)
					+ md5.substring(31, 32);
			String client = time + "_" + zuhe;
			String newMessage = "c=ac&m=none&appid=" + Utils.HONGWAI_APPID
					+ "&f=" + hongWaiId;
			String result = Utils.httpsRequest(Utils.HONGWAI_URL,
					Utils.REQUEST_POST, newMessage, client);

			hongWaiKuService.insertHongWaiRegisterInfolog(hongWaiId, "获取固件激活码",
					time, md5, zuhe, client, newMessage, result);

			JSONObject object = (JSONObject) JSON.parse(result);
			int retCode = object.getIntValue("ret_code");
			String retMsg = object.getString("ret_msg");
			hongWaiKuService.insertHongWaiRegisterInfo(hongWaiId, retCode,
					retMsg);
			pwd.put("retMsg", retMsg);
			if(retCode == 1){
				hongWaiKuService.insertDebiceJihuoInfo(imei,hongWaiId,retMsg);
			}
			
		} else {
			pwd.put("retMsg", jihuo.getDevice_jihuo());
		}

		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		dto.setData(pwd);
		return dto;
	}

}
