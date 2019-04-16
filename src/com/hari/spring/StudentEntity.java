package com.hari.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_DETAILS")
public class StudentEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollNo;
	
	private int age;
	
	private String name;
	
	private int mobileNumber;
	
//	private List<String> hobbies;
//	private Address address;
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

//	public List<String> getHobbies() {
//		return hobbies;
//	}
//	public void setHobbies(List<String> hobbies) {
//		this.hobbies = hobbies;
//	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	
}

