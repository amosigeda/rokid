package com.bracelet.entity;

import java.sql.Timestamp;

public class HongWaiRegister {
	private Long id;
	private String hongwai_id;
	private Timestamp createtime;
	private Integer ret_code;
	private String ret_msg;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getRet_code() {
		return ret_code;
	}
	public void setRet_code(Integer ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	

}
