package com.demo.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.demo.entity.Employee;
import com.demo.exceptionHandling.EmployeeException;
import com.demo.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String addEmployee(Employee employee) {
		Employee employeeObj = employeeRepository.save(employee);
		if(employeeObj.getId()!=null) {
			return "employee created Successfully";
		}
		else {
			return "employee not created";
		}
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		
		return employeeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Employee not found"));
	}

	@Override
	public List<Employee> getAllEmployees(Long length) {
		if(length==null)
		return employeeRepository.findAll();
		else 
			return employeeRepository.findAll().stream().limit(length).collect(Collectors.toList());
	}

	@Override
	public Employee updateEmployee(Employee employee,Integer id) {
		Employee existingEmployee = employeeRepository.findById(id).get();
		existingEmployee.setName(employee.getName());
		existingEmployee.setYear(employee.getYear());
		existingEmployee.setGender(employee.getGender());
		return employeeRepository.save(existingEmployee);
	}

	@Override
	public Employee updateEmployeeByFields(Map<String, Object> fields, Integer id) {
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		if(existingEmployee.isPresent()) {
			fields.forEach((key,value)->{
				Field field = ReflectionUtils.findRequiredField(Employee.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingEmployee.get(), value);
			});
			return employeeRepository.save(existingEmployee.get());
		}
		return null;
	}

	@Override
	public boolean deleteEmployeeById(Integer id) {
		boolean status =false;
		Employee employeeRecord = employeeRepository.findById(id).orElseThrow(()->new EmployeeException("Employee Record Not Found in the Database with id: "+id));
		if(employeeRecord!=null) {
			employeeRepository.deleteById(id);
			status=true;
		}
		return status;
	}

	




}
