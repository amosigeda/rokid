package com.bracelet.entity;

import java.sql.Timestamp;

public class HongWai {
	private Long id;
	private String imei;
	private Long hongwai_id;;
	private Timestamp createtime;
	private String name;
	private Integer num;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Long getHongwai_id() {
		return hongwai_id;
	}

	public void setHongwai_id(Long hongwai_id) {
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
     
}
