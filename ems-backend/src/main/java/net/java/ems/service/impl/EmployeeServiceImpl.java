package net.java.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.java.ems.dto.EmployeeDto;
import net.java.ems.entity.Employee;
import net.java.ems.exception.ResourceNotFoundException;
import net.java.ems.mapper.EmployeeMapper;
import net.java.ems.repository.EmployeeRepository;
import net.java.ems.service.EmployeeService;
//import net.java.ems.service.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		// to save this employee JPA entity into database we use employeeRepository.save
		Employee savedEmployee = employeeRepository.save(employee);
		// Now, we need to return the savedEmployee object back to the client so, we convert this into EmployeeDto
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeByID(Long employeeId) {
		Employee employee= employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given Id: " + employeeId));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}
	
	@Override
	public List<EmployeeDto> getAllEmployees() {
	java.util.List<Employee>employees = employeeRepository.findAll();		
		return  employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
				                                                                     .collect(Collectors.toList());
	}
	
	@Override
	public EmployeeDto updatedEmployee(Long employeeId, EmployeeDto updateEmployee) {
		Employee employee= employeeRepository.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());
		Employee updatedEmployeeObj = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}
	
	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee= employeeRepository.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
		 employeeRepository.deleteById(employeeId);
		
	}
}
