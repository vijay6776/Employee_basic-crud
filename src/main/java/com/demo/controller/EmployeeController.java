package com.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Employee;
import com.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	   
	
	@PostMapping("/add")
	public ResponseEntity<String> saveEmployeeoToDb(@RequestBody Employee employee ){
		 String addEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(addEmployee,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
		Employee employeeById = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employeeById,HttpStatus.OK);
		}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployeees(@RequestParam(name="length",required = false) Long length) {
		
		List<Employee> allEmployees = employeeService.getAllEmployees(length);
//		if(length==null) {
//			return new ResponseEntity(allEmployees.stream().limit(allEmployees.size()).collect(Collectors.toList()) ,HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity(allEmployees.stream().limit(length).collect(Collectors.toList())  ,HttpStatus.OK);
//		}
		return new ResponseEntity<>(allEmployees,HttpStatus.OK);

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateAnEmployee(@RequestBody Employee employee,@PathVariable Integer id){
		Employee updateEmployee = employeeService.updateEmployee(employee,id);
		return new ResponseEntity<Employee>(updateEmployee,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployeeByFields(@RequestBody Map<String,Object> fields,@PathVariable Integer id){
		Employee updateEmployeeByFields = employeeService.updateEmployeeByFields(fields, id);
        return new ResponseEntity<Employee>(updateEmployeeByFields,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		boolean isDeleted = employeeService.deleteEmployeeById(id);
		String msg="";
		if(isDeleted) {
			msg="Employee Deleted Successfully";
		} else {
			msg="Employee Delete Failed";
		}
		return new ResponseEntity<> (msg,HttpStatus.OK);
	}
	
	

}



