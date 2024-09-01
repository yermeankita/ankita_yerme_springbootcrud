package com.springboot.springbootproject.repositories;

import com.springboot.springbootproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
}
