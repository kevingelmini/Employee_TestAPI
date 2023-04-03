package com.example.demo.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domains.Employee;
import com.example.demo.services.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
// //@AllArgsConstructor
// @RequiredArgsConstructor
//@Transactional
@RequestMapping(path = "/api/employee", produces = "application/json")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT }, maxAge = 3600)
public class EmployeeController {
    //DT= --> DATA TRANSFER OBJECT: permette di filtrare le informazioni in uscita
    @Autowired
    EmployeeService employeeService;

    // public EmployeeController(EmployeeService employeeService) {
    //     this.employeeService = employeeService;
    // }

  
    @GetMapping()
    public List<Employee> getEmployees() {
        return this.employeeService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Optional<Employee> opt= this.employeeService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<Employee>(opt.get(),HttpStatus.OK);
        else    
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Employee> add(@RequestBody Employee entity) {
        if (this.employeeService.save(entity).isPresent())
            return new ResponseEntity<Employee>(entity, HttpStatus.CREATED);
        else
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<Employee> update(@RequestBody Employee entity) {
        if (this.employeeService.save(entity).isPresent())
            return new ResponseEntity<Employee>(entity, HttpStatus.CREATED);
        else
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
        if (this.employeeService.delete(id).isPresent())
            return new ResponseEntity<Employee>(HttpStatus.OK);
        else
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{idDip}/{idTask}")
    public ResponseEntity<Employee> addTaskDipendente(@PathVariable Long idDip, @PathVariable Long idTask) {
        Optional<Employee> opt= this.employeeService.addTaskToEmployee(idDip,idTask);
        if (opt.isPresent())
            return new ResponseEntity<Employee>(opt.get(),HttpStatus.OK);
        else    
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

}
