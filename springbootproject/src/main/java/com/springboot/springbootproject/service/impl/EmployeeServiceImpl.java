package com.springboot.springbootproject.service.impl;

import com.springboot.springbootproject.dto.EmployeeDto;
import com.springboot.springbootproject.entity.Employee;
import com.springboot.springbootproject.exception.ResourceNotFoundException;
import com.springboot.springbootproject.mapper.EmployeeMapper;
import com.springboot.springbootproject.repositories.EmployeeRepository;
import com.springboot.springbootproject.service.EmployeeService;
import io.micrometer.observation.Observation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedemployee;
        savedemployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedemployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not exists with given id:"+employeeId));

        Employee employee = null;
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
      List<Employee> employees=  employeeRepository.findAll();
        return (List<EmployeeDto>) employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public <ExampleDto> EmployeeDto updateEmployee(Long employeeId, ExampleDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        {
            Observation.CheckedCallable<ResourceNotFoundException, Throwable> resourceNotFoundExceptionThrowableCheckedCallable = () -> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId);
        };
        employee.setFirstName(String.valueOf(updateEmployee.getClass()));
        employee.setLastName(String.valueOf(updateEmployee.getClass()));
        employee.setEmail(String.valueOf(updateEmployee.getClass()));
        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        {
            Observation.CheckedCallable<ResourceNotFoundException, Throwable> resourceNotFoundExceptionThrowableCheckedCallable = () -> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId);
        };
        employeeRepository.deleteById(employeeId);

    }


}
