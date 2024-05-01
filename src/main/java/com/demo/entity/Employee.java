package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee_Info")
public class Employee {
	
	public Employee() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Emp_Id")
	private Integer id;
	
	public Employee(Integer id, String name, Integer year, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.gender = gender;
	}
	@Column(name="Emp_Name")
    private String name;
	@Column(name="Joining_Year")
    private Integer year;
	@Column(name="Emp_Gender")
    private String gender;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", year=" + year + ", gender=" + gender + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
    
}
