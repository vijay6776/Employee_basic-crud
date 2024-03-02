package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.entity.Employee;

public interface EmployeeService {
	public String addEmployee(Employee employee);
	public Employee getEmployeeById(Integer id);

	public List<Employee> getAllEmployees(Long length);
//	
	public Employee updateEmployee(Employee employee,Integer Id);
//	
	public Employee updateEmployeeByFields(Map<String,Object> fields,Integer id);
//	
	public boolean deleteEmployeeById(Integer id);
	/********************^^^^^^^^^^^^^^^^*************************/
   
}
