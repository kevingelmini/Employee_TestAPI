package com.example.demo.controllers;



import java.util.List;
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

import com.example.demo.domains.*;
import com.example.demo.services.*;

import jakarta.transaction.Transactional;;  
@RestController
@Transactional
@RequestMapping(path = "/api/project", produces = "application/json")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT }, maxAge = 3600)

public class ProjectController {
    @Autowired
    ProjectService projectService;

    // public ProjectController(ProjectService projectService) {
    //     this.projectService = projectService;
    // }

    @GetMapping()
    public List<Project> getEmployees() {
        return this.projectService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Project> getEmployee(@PathVariable Long id) {
        Optional<Project> opt= this.projectService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<Project>(opt.get(),HttpStatus.OK);
        else    
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Project> add(@RequestBody Project entity) {
        if (this.projectService.save(entity).isPresent())
            return new ResponseEntity<Project>(entity, HttpStatus.CREATED);
        else
            return new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<Project> update(@RequestBody Project entity) {
        if (this.projectService.save(entity).isPresent())
            return new ResponseEntity<Project>(entity, HttpStatus.CREATED);
        else
            return new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> delete(@PathVariable Long id) {
        if (this.projectService.delete(id).isPresent())
            return new ResponseEntity<Project>(HttpStatus.OK);
        else
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
    }
}
