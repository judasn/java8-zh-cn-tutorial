package com.youmeek.java.action.pojo;

import java.io.Serializable;


public class Authority implements Serializable {

	private static final long serialVersionUID = 6215500909993062248L;
	
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Authority{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
