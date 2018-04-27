package com.youmeek.java.sample.pojo;

import java.util.Date;

public class StudentDTO {

	private int myId;
	private String myName;
	private int myAge;
	private Date myCreateDatetime;

	public StudentDTO() {
	}

	public StudentDTO(int myId, String myName, int myAge, Date myCreateDatetime) {
		this.myId = myId;
		this.myName = myName;
		this.myAge = myAge;
		this.myCreateDatetime = myCreateDatetime;
	}

	public int getMyId() {
		return myId;
	}

	public void setMyId(int myId) {
		this.myId = myId;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getMyAge() {
		return myAge;
	}

	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	public Date getMyCreateDatetime() {
		return myCreateDatetime;
	}

	public void setMyCreateDatetime(Date myCreateDatetime) {
		this.myCreateDatetime = myCreateDatetime;
	}
}
