package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.AllKeyInfo;
import com.bracelet.entity.HongWai;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/infrared")
public class IInfraredController extends BaseController {

	@Autowired
	ILocationService locationService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IStepService stepService;
	@Autowired
	IHongWaiKuService hongWaiKuService;
	@Resource
	BaseChannelHandler baseChannelHandler;
	private Logger logger = LoggerFactory.getLogger(getClass());

	// 获取红外id
	@ResponseBody
	@RequestMapping(value = "/getDeviceId/{token}/{imei}", method = RequestMethod.GET)
	public HttpBaseDto getDeviceId(@PathVariable String token,
			@PathVariable String imei) {
		Long user_id = checkTokenAndUser(token);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		Map<String, Object> map = new HashMap<>();
		HongWai hongWaiInfo = userInfoService.getHongWaiInfo(imei);
		if (hongWaiInfo != null) {
			map.put("id", hongWaiInfo.getHongwai_id()+"");
		} else {
			map.put("id", "0");

			LocationRequest re = new LocationRequest();
			re.setA(0);
			re.setTimestamp(System.currentTimeMillis() / 1000);
			re.setType(33);
			re.setNo(RanomUtil.getFixLenthString(10));

			if (socketLoginDto.getChannel().isActive()) {
				socketLoginDto.getChannel().writeAndFlush(
						JSON.toJSONString(re) + "\r\n");
				logger.info("===request getLocation...ip:"
						+ socketLoginDto.getChannel().remoteAddress()
								.toString() + ",data:" + JSON.toJSONString(re));
			} else {
				logger.info("socketLoginDto error.no login.not active.token:"
						+ imei);
				throw new BizException(RespCode.DEV_NOT_LOGIN);
			}
		}

		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(map);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/startMatch", method = RequestMethod.POST)
	public HttpBaseDto startMatch(@RequestParam String token,
			@RequestParam String imei, @RequestParam String src) {
		Long user_id = checkTokenAndUser(token);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(34);
		re.setNo(RanomUtil.getFixLenthString(10));
		re.setSrc(src);

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
	
	@ResponseBody
	@RequestMapping(value = "/nextMatch", method = RequestMethod.POST)
	public HttpBaseDto nextMatch(@RequestParam String token,
			@RequestParam String imei, @RequestParam String src) {
		Long user_id = checkTokenAndUser(token);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(35);
		re.setNo(RanomUtil.getFixLenthString(10));
		re.setSrc(src);

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
	
	
	@ResponseBody
	@RequestMapping(value = "/setname", method = RequestMethod.POST)
	public HttpBaseDto setName(@RequestParam String token,
			@RequestParam String imei, @RequestParam String id, @RequestParam String  name) {
		Long user_id = checkTokenAndUser(token);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(36);
		re.setNo(RanomUtil.getFixLenthString(10));
		re.setName(name);

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(
					JSON.toJSONString(re) + "\r\n");
			logger.info("===request getLocation...ip:"
					+ socketLoginDto.getChannel().remoteAddress().toString()
					+ ",data:" + JSON.toJSONString(re));
			
			userInfoService.updateHongWaiInfo(imei,id,name);
		} else {
			logger.info("socketLoginDto error.no login.not active.token:"
					+ imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}
	
	
	//红外控制
	@ResponseBody
	@RequestMapping(value = "/control", method = RequestMethod.POST)
	public HttpBaseDto control(@RequestParam String token,
			@RequestParam String imei, @RequestParam String id, @RequestParam String  name, @RequestParam String  key) {
		Long user_id = checkTokenAndUser(token);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(37);
		re.setNo(RanomUtil.getFixLenthString(10));
		re.setName(name);
		re.setKey(key);

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(
					JSON.toJSONString(re) + "\r\n");
			logger.info("===request getLocation...ip:"
					+ socketLoginDto.getChannel().remoteAddress().toString()
					+ ",data:" + JSON.toJSONString(re));
			
			userInfoService.updateHongWaiInfo(imei,id,name);
		} else {
			logger.info("socketLoginDto error.no login.not active.token:"
					+ imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}
	
	// 获取匹配总数
		@ResponseBody
		@RequestMapping(value = "/getMatchNumber/{token}/{imei}", method = RequestMethod.GET)
		public HttpBaseDto getMatchNumber(@PathVariable String token,
				@PathVariable String imei) {
			Long user_id = checkTokenAndUser(token);
			Map<String, Object> map = new HashMap<>();
			HongWai hong = userInfoService.getHongWaiInfo(imei);
			if(hong != null){
				map.put("num", hong.getNum());
				map.put("rs", hong.getRs());
			}else{
				map.put("num", 0);
				map.put("rs", "");
			}

			HttpBaseDto dto = new HttpBaseDto();
			dto.setData(map);
			return dto;
		}
		
		
		//获取匹配型号总数allnum   post
		@ResponseBody
		@RequestMapping(value = "/getPiPeiAllNum", method = RequestMethod.POST)
		public HttpBaseDto getPiPeiAllNum(@RequestParam String token,@RequestParam String imei,
				 @RequestParam Integer bid,	 @RequestParam Integer t, @RequestParam String  hongWaiId) {
			Long user_id = checkTokenAndUser(token);
			Map<String, Object> map = new HashMap<>();
		
			String time = System.currentTimeMillis() / 1000 + "";
			String md5 = Utils.getmd5("none" + bid + t + 4 + hongWaiId + time);
			String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
					+ md5.substring(7, 8) + md5.substring(15, 16)
					+ md5.substring(31, 32);
			String client = time + "_" + zuhe;
			String newMessage = "c=i&m=none&bid="+bid+"&t="+t+"&v=4"+"&appid=" + Utils.HONGWAI_APPID
					+ "&f=" + hongWaiId;
			String result = Utils.httpsRequest(Utils.HONGWAI_URL,
					Utils.REQUEST_POST, newMessage, client);

			hongWaiKuService.insertHongWaiRegisterInfolog(hongWaiId,
					"根据品牌ID、设备类型获取遥控器列表", time, md5, zuhe, client, newMessage, result);

			JSONObject object = (JSONObject) JSON.parse(result);
			int sum = object.getIntValue("ret_code");
			String rs = object.getString("rs");

			userInfoService.updateHongWaiNumInfo(imei, sum, rs);
			
			map.put("num", sum);
			map.put("rs", rs);
			
			HttpBaseDto dto = new HttpBaseDto();
			dto.setData(map);
			return dto;
		
		}
		
		
		
		@ResponseBody
		@RequestMapping(value = "/getallkey/{token}/{imei}", method = RequestMethod.GET)
		public HttpBaseDto getAllKey(@PathVariable String token,@PathVariable String imei) {
			Long user_id = checkTokenAndUser(token);
			Map<String, Object> map = new HashMap<>();
			AllKeyInfo hong = userInfoService.getAllKeyInfo(imei);
			if(hong != null){
				map.put("allkey", hong.getAll_key()+"");
			}else{
				map.put("allkey", "");
			}

			HttpBaseDto dto = new HttpBaseDto();
			dto.setData(map);
			return dto;
		}
		

}
