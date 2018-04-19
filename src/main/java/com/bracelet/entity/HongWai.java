package com.bracelet.entity;

import java.sql.Timestamp;

public class HongWai {
	private Integer idd;
	private String id;
	private String imei;
	private String hongwai_id;;
	private Timestamp createtime;
	private Timestamp updatetime;
	private String name;
	private Integer num;
	

	public Integer getIdd() {
		return idd;
	}

	public void setIdd(Integer idd) {
		this.idd = idd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}
