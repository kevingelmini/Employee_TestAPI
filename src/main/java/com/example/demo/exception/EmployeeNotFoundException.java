package com.example.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long employee_id) {
        super("Employee with id: "+ employee_id+" not found");
    }
}