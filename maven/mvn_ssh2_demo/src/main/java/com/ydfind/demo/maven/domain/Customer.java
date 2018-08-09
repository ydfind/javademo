package com.ydfind.demo.maven.domain;

public class Customer {
	private String custId;
	private String custName;
	private String address;

	public String getCustName() {
		return custName;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
