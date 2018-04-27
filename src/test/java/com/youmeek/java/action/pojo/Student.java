package com.youmeek.java.action.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable {

	private static final long serialVersionUID = -2651299932302968227L;
	
	private Integer id;
	private String name;
	private Integer age;
	private Long createDatetime;
	private Set<Authority> authorities = new HashSet<>();

	public Student() {

	}

	public Student(Integer id, String name, Integer age, Long createDatetime, Set<Authority> authorities) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.createDatetime = createDatetime;
		this.authorities = authorities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Long createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", createDatetime=" + createDatetime +
				", authorities=" + authorities +
				'}';
	}
}
