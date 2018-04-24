package com.bracelet.entity;

public class InfraredRequest {
	private int a = 0;
	private String no;
	private int type;
	private long timestamp;
	private Integer size;
	private byte[] b;
	private String result;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public byte[] getB() {
		return b;
	}

	public void setB(byte[] b) {
		this.b = b;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
}
