package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.LocationRequest;
import com.bracelet.exception.BizException;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IStepService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.BaseChannelHandler;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.RanomUtil;
import com.bracelet.util.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@RequestMapping("/woshiskill")
public class WoShiSkillController extends BaseController {

	@Autowired
	ILocationService locationService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IStepService stepService;
	@Resource
	BaseChannelHandler baseChannelHandler;
	private Logger logger = LoggerFactory.getLogger(getClass());

    //开灯
	@ResponseBody
	@RequestMapping(value = "/openlight/{imei}", method = RequestMethod.GET)
	public HttpBaseDto openlight(@PathVariable String imei) {
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(38);
		re.setNo(RanomUtil.getFixLenthString(10));

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			logger.info("===request openlight...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
					+ JSON.toJSONString(re));
		} else {
			logger.info("socketLoginDto error.no openlight.not active.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}
	//关灯
	@ResponseBody
	@RequestMapping(value = "/closelight/{imei}", method = RequestMethod.GET)
	public HttpBaseDto closelight(@PathVariable String imei) {
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(39);
		re.setNo(RanomUtil.getFixLenthString(10));

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			logger.info("===request closelight...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
					+ JSON.toJSONString(re));
		} else {
			logger.info("socketLoginDto error.no closelight.not active.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}
	//打开电视
	@ResponseBody
	@RequestMapping(value = "/opentv/{imei}", method = RequestMethod.GET)
	public HttpBaseDto opentv(@PathVariable String imei) {
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(40);
		re.setNo(RanomUtil.getFixLenthString(10));

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
					+ JSON.toJSONString(re));
		} else {
			logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}
	
	    //关闭电视
		@ResponseBody
		@RequestMapping(value = "/closetv/{imei}", method = RequestMethod.GET)
		public HttpBaseDto closetv(@PathVariable String imei) {
			SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
			if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
				logger.info("socketLoginDto error.no login.token:" + imei);
				throw new BizException(RespCode.DEV_NOT_LOGIN);
			}
			LocationRequest re = new LocationRequest();
			re.setA(0);
			re.setTimestamp(System.currentTimeMillis() / 1000);
			re.setType(41);
			re.setNo(RanomUtil.getFixLenthString(10));

			if (socketLoginDto.getChannel().isActive()) {
				socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
				logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
						+ JSON.toJSONString(re));
			} else {
				logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
				throw new BizException(RespCode.DEV_NOT_LOGIN);
			}
			HttpBaseDto dto = new HttpBaseDto();
			return dto;
		}
		
	    //音量加
			@ResponseBody
			@RequestMapping(value = "/addvoice/{imei}", method = RequestMethod.GET)
			public HttpBaseDto addvoice(@PathVariable String imei) {
				SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
				if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
					logger.info("socketLoginDto error.no login.token:" + imei);
					throw new BizException(RespCode.DEV_NOT_LOGIN);
				}
				LocationRequest re = new LocationRequest();
				re.setA(0);
				re.setTimestamp(System.currentTimeMillis() / 1000);
				re.setType(42);
				re.setNo(RanomUtil.getFixLenthString(10));

				if (socketLoginDto.getChannel().isActive()) {
					socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
					logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
							+ JSON.toJSONString(re));
				} else {
					logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
					throw new BizException(RespCode.DEV_NOT_LOGIN);
				}
				HttpBaseDto dto = new HttpBaseDto();
				return dto;
			}
			  //音量-
			@ResponseBody
			@RequestMapping(value = "/reducevoice/{imei}", method = RequestMethod.GET)
			public HttpBaseDto reducevoice(@PathVariable String imei) {
				SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
				if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
					logger.info("socketLoginDto error.no login.token:" + imei);
					throw new BizException(RespCode.DEV_NOT_LOGIN);
				}
				LocationRequest re = new LocationRequest();
				re.setA(0);
				re.setTimestamp(System.currentTimeMillis() / 1000);
				re.setType(43);
				re.setNo(RanomUtil.getFixLenthString(10));

				if (socketLoginDto.getChannel().isActive()) {
					socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
					logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
							+ JSON.toJSONString(re));
				} else {
					logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
					throw new BizException(RespCode.DEV_NOT_LOGIN);
				}
				HttpBaseDto dto = new HttpBaseDto();
				return dto;
			}
		
			
			
			  //频道加
					@ResponseBody
					@RequestMapping(value = "/addpindao/{imei}", method = RequestMethod.GET)
					public HttpBaseDto addpindao(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(44);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					
					//频道减
					@ResponseBody
					@RequestMapping(value = "/reducepindao/{imei}", method = RequestMethod.GET)
					public HttpBaseDto reducepindao(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(45);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}	
					
					//静音
					@ResponseBody
					@RequestMapping(value = "/mutevoice/{imei}", method = RequestMethod.GET)
					public HttpBaseDto mutevoice(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(46);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}

					//打开空调
					@ResponseBody
					@RequestMapping(value = "/openkt/{imei}", method = RequestMethod.GET)
					public HttpBaseDto openkt(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(47);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}


					//关闭空调
					@ResponseBody
					@RequestMapping(value = "/closekt/{imei}", method = RequestMethod.GET)
					public HttpBaseDto closekt(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(48);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					//温度加
					@ResponseBody
					@RequestMapping(value = "/addtemp/{imei}", method = RequestMethod.GET)
					public HttpBaseDto addtemp(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(49);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度减
					@ResponseBody
					@RequestMapping(value = "/reducetemp/{imei}", method = RequestMethod.GET)
					public HttpBaseDto reducetemp(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(50);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}

					//自动模式
					@ResponseBody
					@RequestMapping(value = "/zidongmode/{imei}", method = RequestMethod.GET)
					public HttpBaseDto zidongmode(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(51);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//抽湿模式
					@ResponseBody
					@RequestMapping(value = "/choushimode/{imei}", method = RequestMethod.GET)
					public HttpBaseDto choushimode(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(52);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//送风模式
					@ResponseBody
					@RequestMapping(value = "/songfengmode/{imei}", method = RequestMethod.GET)
					public HttpBaseDto songfengmode(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(53);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//制冷模式
					@ResponseBody
					@RequestMapping(value = "/zhilengmode/{imei}", method = RequestMethod.GET)
					public HttpBaseDto zhilengmode(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(54);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//制热模式
					@ResponseBody
					@RequestMapping(value = "/zhiremode/{imei}", method = RequestMethod.GET)
					public HttpBaseDto zhiremode(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(55);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//风力自动
					@ResponseBody
					@RequestMapping(value = "/fenglizidongmode/{imei}", method = RequestMethod.GET)
					public HttpBaseDto fenglizidongmode(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(56);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//风力调到一级
					@ResponseBody
					@RequestMapping(value = "/wildturnone/{imei}", method = RequestMethod.GET)
					public HttpBaseDto wildturnone(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(57);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//风力调到二级
					@ResponseBody
					@RequestMapping(value = "/wildturntwo/{imei}", method = RequestMethod.GET)
					public HttpBaseDto wildturntwo(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(58);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//风力调到三级
					@ResponseBody
					@RequestMapping(value = "/wildturnthree/{imei}", method = RequestMethod.GET)
					public HttpBaseDto wildturnthree(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(59);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//打开上下扫风
					@ResponseBody
					@RequestMapping(value = "/openshangxiafeng/{imei}", method = RequestMethod.GET)
					public HttpBaseDto openshangxiafeng(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(60);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//关闭上下扫风
					@ResponseBody
					@RequestMapping(value = "/closeshangxiafeng/{imei}", method = RequestMethod.GET)
					public HttpBaseDto closeshangxiafeng(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(61);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//打开左右扫风
					@ResponseBody
					@RequestMapping(value = "/openzuoyoufeng/{imei}", method = RequestMethod.GET)
					public HttpBaseDto openzuoyoufeng(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(62);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//关闭左右扫风
					@ResponseBody
					@RequestMapping(value = "/closezuoyoufeng/{imei}", method = RequestMethod.GET)
					public HttpBaseDto closezuoyoufeng(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(63);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					
				
					
					//温度调到16度
					@ResponseBody
					@RequestMapping(value = "/turnfifteen/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntempten(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(64);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}

					//温度调到17度
					@ResponseBody
					@RequestMapping(value = "/turnsevteen/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turnsevteen(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(65);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到18度
					@ResponseBody
					@RequestMapping(value = "/turneigteen/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turneigteen(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(66);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到19度
					@ResponseBody
					@RequestMapping(value = "/turnnineteen/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turnnineteen(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(67);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到20度
					@ResponseBody
					@RequestMapping(value = "/turntwenty/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwenty(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(68);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到21度
					@ResponseBody
					@RequestMapping(value = "/turntwentyone/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentyone(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(69);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到22度
					@ResponseBody
					@RequestMapping(value = "/turntwentytwo/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentytwo(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(70);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					
					//温度调到23度
					@ResponseBody
					@RequestMapping(value = "/turntwentythree/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentythree(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(71);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到24度
					@ResponseBody
					@RequestMapping(value = "/turntwentyfour/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentyfour(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(72);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到25度
					@ResponseBody
					@RequestMapping(value = "/turntwentyfive/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentyfive(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(73);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到26度
					@ResponseBody
					@RequestMapping(value = "/turntwentysix/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentysix(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(74);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					
					//温度调到27度
					@ResponseBody
					@RequestMapping(value = "/turntwentyseven/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentyseven(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(75);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					
					//温度调到28度
					@ResponseBody
					@RequestMapping(value = "/turntwentyeight/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentyeight(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(76);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					//温度调到29度
					@ResponseBody
					@RequestMapping(value = "/turntwentynine/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntwentynine(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(77);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request turn twentyone...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}
					
					
					//温度调到30度
					@ResponseBody
					@RequestMapping(value = "/turntempthrity/{imei}", method = RequestMethod.GET)
					public HttpBaseDto turntempthrity(@PathVariable String imei) {
						SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
						if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
							logger.info("socketLoginDto error.no login.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						LocationRequest re = new LocationRequest();
						re.setA(0);
						re.setTimestamp(System.currentTimeMillis() / 1000);
						re.setType(78);
						re.setNo(RanomUtil.getFixLenthString(10));

						if (socketLoginDto.getChannel().isActive()) {
							socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
							logger.info("===request opentv...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
									+ JSON.toJSONString(re));
						} else {
							logger.info("socketLoginDto error.no opentv.not active.token:" + imei);
							throw new BizException(RespCode.DEV_NOT_LOGIN);
						}
						HttpBaseDto dto = new HttpBaseDto();
						return dto;
					}




}
