package com.treepal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



@Entity
public class Tree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Name cannot be empty")
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column
	private int age;
	
	@Column
	private int todayGrowth;

	@Transient
	private String fakeId;

	
	public Tree() {
		this.age = 0;
		this.todayGrowth = 0;
	}

	public Tree(String name) {
		this.name = name;
		this.age = 0;
		this.todayGrowth = 0;
		this.fakeId = "";
	}
	
	public Tree(Long id, String name) {
		this.id = id;
		this.name = name;
		this.age = 0;
		this.todayGrowth = 0;
		this.fakeId = "";
	}

	public Tree(String fakeId, String name) {
		this.fakeId = fakeId;
		this.name = name;
		this.age = 0;
		this.todayGrowth = 0;

	}


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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getTodayGrowth() {
		return this.todayGrowth;
	}
	
	public void setTodayGrowth(int todayGrowth) {
		this.todayGrowth = todayGrowth;
	}

	public String getFakeId() {
		return fakeId;
	}

	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}
}
