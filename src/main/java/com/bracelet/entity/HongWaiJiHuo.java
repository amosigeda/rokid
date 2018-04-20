package com.bracelet.entity;

import java.sql.Timestamp;

public class HongWaiJiHuo {
	private Integer id;
	private String imei;
	private String hongwai_id;
	private String device_jihuo;
	private Timestamp createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getHongwai_id() {
		return hongwai_id;
	}
	public void setHongwai_id(String hongwai_id) {
		this.hongwai_id = hongwai_id;
	}
	public String getDevice_jihuo() {
		return device_jihuo;
	}
	public void setDevice_jihuo(String device_jihuo) {
		this.device_jihuo = device_jihuo;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
