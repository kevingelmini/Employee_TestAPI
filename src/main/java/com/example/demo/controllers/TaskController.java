
package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
import com.example.demo.domains.records.TaskRecord;
import com.example.demo.services.*;


import jakarta.transaction.Transactional;


@RestController
//,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE
//@RequestMapping(path = "/api/task", produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/api/task", produces = "application/json")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT }, maxAge = 3600)

public class TaskController {   
    TaskService taskService;
    // @Autowired
    // TaskRepository t;
    //DI
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<List<Task>>(this.taskService.findAll(),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Task> getEmployee(@PathVariable Long id) {
        Optional<Task> opt= this.taskService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<Task>(opt.get(),HttpStatus.OK);
        else    
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
    }
    @Transactional 


    @PostMapping("")
    public ResponseEntity<Task> add(@RequestBody TaskRecord entity) {

        Optional<Task> t=this.taskService.save(entity);
        if (t.isPresent())
            return new ResponseEntity<Task>(t.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
        
    }
    
    // @PostMapping("")
    // public ResponseEntity<TaskRecord> add(@RequestBody TaskRecord entity) {
        
    //     if (this.taskService.save(entity).isPresent())
    //         return new ResponseEntity<TaskRecord>(entity, HttpStatus.CREATED);
    //     else
    //         return new ResponseEntity<TaskRecord>(HttpStatus.BAD_REQUEST);
    // }
    // // @PutMapping("")
    // public ResponseEntity<Task> adda(@RequestBody TaskRecord entity) {
    //     Optional<Task> t= this.taskService.save(entity);
    //     if (t.isPresent())
    //         return new ResponseEntity<Task>(t.get(), HttpStatus.CREATED);
    //     else
    //         return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
    // }
    
    
    
    // @PutMapping("")
    // public ResponseEntity<Task> update(@RequestBody Task entity) {
    //     if (this.taskService.save(entity).isPresent())
    //         return new ResponseEntity<Task>(entity, HttpStatus.CREATED);
    //     else
    //         return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        if (this.taskService.delete(id).isPresent())
            return new ResponseEntity<Task>(HttpStatus.OK);
        else
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
    }
}
