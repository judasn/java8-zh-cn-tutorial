package com.youmeek.java.action.pojo;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentDTO {

	private Integer myId;
	private String myName;
	private Integer myAge;
	private Long myCreateDatetime;
	private Set<String> authorities;

	public StudentDTO() {
	}

	public StudentDTO(Integer myId, String myName, Integer myAge, Long myCreateDatetime) {
		this.myId = myId;
		this.myName = myName;
		this.myAge = myAge;
		this.myCreateDatetime = myCreateDatetime;
	}

	public StudentDTO(Student student) {
		this.myId = student.getId();
		this.myName = student.getName();
		this.myAge = student.getAge();
		this.myCreateDatetime = student.getCreateDatetime();
		this.authorities = student.getAuthorities().stream()
				.map(Authority::getName)
				.collect(Collectors.toSet());
	}

	public Integer getMyId() {
		return myId;
	}

	public void setMyId(Integer myId) {
		this.myId = myId;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Integer getMyAge() {
		return myAge;
	}

	public void setMyAge(Integer myAge) {
		this.myAge = myAge;
	}

	public Long getMyCreateDatetime() {
		return myCreateDatetime;
	}

	public void setMyCreateDatetime(Long myCreateDatetime) {
		this.myCreateDatetime = myCreateDatetime;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
}
