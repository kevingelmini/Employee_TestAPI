package com.example.demo.services;
import java.util.*;
import com.example.demo.domains.*;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.ProjectRepo;

@Service
public class ProjectService {
    
    ProjectRepo projectRepo;
    EmployeeRepo employeeRepo;
    public ProjectService(ProjectRepo projectRepo,EmployeeRepo employeeRepo){
        this.projectRepo=projectRepo;
        this.employeeRepo=employeeRepo;
    }
    public ProjectService(){}
    public List<Project> findAll(){
        return this.projectRepo.findAll();
    }

    public Optional<Project>findById(Long id){
        Optional<Project> opt=this.projectRepo.findById(id);
        return opt;
    } 
    public Optional<Project> save(Project entity ){
        Optional<Project> opt= Optional.ofNullable(null);
        Optional<Employee> optE=this.employeeRepo.findById(entity.getLeader_id());
        if (optE.isPresent()){
            entity.setLeader(optE.get());
            Project project=this.projectRepo.save(entity);
            opt=Optional.ofNullable(project);
    
        }
        return opt;

    }
    public Optional<Project> update(Project entity ){
        Project project=this.projectRepo.save(entity);
        Optional<Project> opt=Optional.ofNullable(project);
        return opt;
    }
    public Optional<Project>delete(Long id){
        Optional<Project> opt=this.projectRepo.findById(id);
        if (opt.isPresent()){
            this.projectRepo.deleteById(id);
        }
        return opt;
    }
}
