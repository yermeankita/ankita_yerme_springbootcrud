package com.springboot.springbootproject.service;
import com.springboot.springbootproject.dto.EmployeeDto;
import com.springboot.springbootproject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    <ExampleDto> EmployeeDto updateEmployee(Long employeeId, ExampleDto updateEmployee);
    void deleteEmployee(Long employeeId);
}
