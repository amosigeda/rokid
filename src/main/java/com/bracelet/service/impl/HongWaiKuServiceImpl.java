package com.bracelet.service.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bracelet.entity.HongWaiJiHuo;
import com.bracelet.entity.HongWaiRegister;
import com.bracelet.entity.Location;
import com.bracelet.entity.Sys;
import com.bracelet.service.IHongWaiKuService;
import com.bracelet.service.ISysService;
import com.bracelet.util.Utils;

@Service
public class HongWaiKuServiceImpl implements IHongWaiKuService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public HongWaiRegister getHongWaiRegisterInfo(String hongWaiId) {
		String sql = "select * from hongwai_register where hongwai_id=? order by id desc LIMIT 1";
		List<HongWaiRegister> list = jdbcTemplate.query(sql, new Object[] { hongWaiId },
				new BeanPropertyRowMapper<HongWaiRegister>(HongWaiRegister.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + hongWaiId);
		}
		return null;
	}

	@Override
	public boolean insertHongWaiRegisterInfolog(String hongWaiId,
			String name, String time, String md5, String zuhe, String client,
			String newMessage, String result) {

		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into hongwai_caozuo_log (hongwai_id, name, time, md5, zuhe, client, new_message, result, createtime) values (?,?,?,?,?,?,?,?,?)",
				new Object[] { hongWaiId, name, time, md5, zuhe, client, newMessage, result, now }, 
				new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
		
	}

	@Override
	public boolean insertHongWaiRegisterInfo(String hongWaiId, int retCode,
			String retMsg) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into hongwai_register (hongwai_id, ret_code, ret_msg, registertime) values (?,?,?,?)",
				new Object[] { hongWaiId, retCode, retMsg, now }, 
				new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
		
	}

	@Override
	public HongWaiJiHuo getHongWaiJiHuoInfoByImeiAndDeviceId(String imei,
			String hongWaiId) {
		String sql = "select * from hongwai_jihuo where imei=? and hongwai_id=? order by id desc LIMIT 1";
		List<HongWaiJiHuo> list = jdbcTemplate.query(sql, new Object[] { imei ,hongWaiId },
				new BeanPropertyRowMapper<HongWaiJiHuo>(HongWaiJiHuo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + hongWaiId);
		}
		return null;
	}

	@Override
	public boolean insertDebiceJihuoInfo(String imei, String hongWaiId,
			String retMsg) {

		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into hongwai_jihuo ( imei, hongwai_id, device_jihuo, createtime) values (?,?,?,?)",
				new Object[] { imei, hongWaiId, retMsg, now }, 
				new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
		
	}





}
