package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domains.Employee;

import jakarta.transaction.Transactional;

//@Transactional
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
