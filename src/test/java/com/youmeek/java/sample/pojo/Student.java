package com.youmeek.java.sample.pojo;

import java.util.Date;

public class Student {

	private int id;
	private String name;
	private int age;
	private Date createDatetime;
	
	public Student() {

	}
	
	public Student(int id, String name, int age, Date createDatetime) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.createDatetime = createDatetime;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", createDatetime=" + createDatetime +
				'}';
	}

}
