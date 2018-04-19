package com.bracelet.entity;

import java.sql.Timestamp;

public class BindDevice {
	
	private Integer id;
	private Long user_id;
	private String  imei;
	private String name;
	private Timestamp createtime;
	private Integer status;
	private Integer type;
	private String mac;
	private Integer bluetooth_status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Integer getBluetooth_status() {
		return bluetooth_status;
	}
	public void setBluetooth_status(Integer bluetooth_status) {
		this.bluetooth_status = bluetooth_status;
	}
	
	
	
}
