package com.bracelet.service;

import com.bracelet.entity.HongWaiJiHuo;
import com.bracelet.entity.HongWaiRegister;


public interface IHongWaiKuService {

	HongWaiRegister getHongWaiRegisterInfo(String hongWaiId);

	

	boolean insertHongWaiRegisterInfolog(String hongWaiId, String string,
			String time, String md5, String zuhe, String client,
			String newMessage, String result);



	boolean insertHongWaiRegisterInfo(String hongWaiId, int retCode, String retMsg);



	HongWaiJiHuo getHongWaiJiHuoInfoByImeiAndDeviceId(String imei,
			String hongWaiId);



	boolean insertDebiceJihuoInfo(String imei, String hongWaiId, String retMsg);

	
}
