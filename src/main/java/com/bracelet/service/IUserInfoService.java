package com.bracelet.service;

import java.util.List;

import com.bracelet.entity.BindDevice;
import com.bracelet.entity.HongWai;
import com.bracelet.entity.NotRegisterInfo;
import com.bracelet.entity.UserInfo;

public interface IUserInfoService {

	boolean insert(String tel);

	boolean bindDevice(Long user_id, String imei,Integer status,String name);

	boolean unbindDevice(Long user_id,Integer id);

	UserInfo getUserInfoByImei(String imei);

	UserInfo getUserInfoById(Long id);

	UserInfo getUserInfoByUsername(String username);

	boolean saveUserPassword(Long user_id, String md5);

	boolean updateUserInfo(Long user_id, String avatar, String nickname,
			Integer intSex, String weight, String height, String address);

	boolean saveUserInfo(String tel, String password,Integer type);

	List<BindDevice> getBindInfoById(Long user_id);

	List<BindDevice> getBindInfoByImei(String imei);

	boolean updateUserPassword(String tel, String password);

	boolean insertNotRegistUser(String tel, String name, Long user_id,String imei);

	NotRegisterInfo getNotRegistUserIdByCondition(String tel, String name,
			Long user_id, String imei);

	NotRegisterInfo getNotRegistUserIdByCondition(Long userid);

	boolean updateUserInfoHeadAndNickName(Long user_id, String nickname,
			String head);

	BindDevice getBindInfoByUserIdAndImei(Long user_id, String imei,Integer status);

	boolean deleteByImei(String imei);

	boolean updatePwdAndType(String tel, String pwd, Integer type);

	BindDevice getBindInfoByImeiAndStatus(String imei, Integer status);

	boolean updateName(Long id, String name);

	BindDevice getBindInfoByImeiAndUserId(String imei, Long user_id);

	HongWai getHongWaiInfo(String imei);

	boolean insertInfraredDeviceId(String imei, Long hongWaiId);

	boolean updateHongWaiInfo(String imei, Long id, String name);

	boolean updateHongWaiNumInfo(String imei, Integer num);

}
