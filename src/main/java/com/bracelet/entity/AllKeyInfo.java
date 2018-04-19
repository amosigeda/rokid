package com.bracelet.entity;

import java.sql.Timestamp;

public class AllKeyInfo {
	private Integer id;
	private String imei;
	private String all_key;;
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
	public String getAll_key() {
		return all_key;
	}
	public void setAll_key(String all_key) {
		this.all_key = all_key;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	
}
