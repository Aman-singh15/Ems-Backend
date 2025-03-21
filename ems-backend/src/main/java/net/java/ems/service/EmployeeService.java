package net.java.ems.service;

import java.util.List;

import net.java.ems.dto.EmployeeDto;

public interface EmployeeService {
   
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeByID(Long employeeId);
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updatedEmployee(Long employeeId, EmployeeDto updateEmployee);
	
	void deleteEmployee(Long employeeId);
}
