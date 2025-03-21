package net.java.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
