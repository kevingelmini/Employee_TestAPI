package com.example.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
// @NoArgsConstructor

// @AllArgsConstructor

//@RequiredArgsConstructor
//public class EmployeeServiceImpl  implements EmployeeService{
public class EmployeeService{
        @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    TaskRepository taskRepo;

    // public EmployeeService(EmployeeRepo employeeRepo,TaskRepository taskRepo){
    //     this.employeeRepo=employeeRepo;
    //     this.taskRepo=taskRepo;
    // }

    public List<Employee> findAll(){
        return this.employeeRepo.findAll();
    }
    public Optional<Employee>findById(Long id){
        Optional<Employee> opt=this.employeeRepo.findById(id);
        return opt;
    } 

    public Optional<Employee> save(Employee entity ){
        Employee employee=this.employeeRepo.save(entity);
        Optional<Employee> opt=Optional.ofNullable(employee);
        return opt;
    }
    public Optional<Employee> update(Employee entity ){
        Employee employee=this.employeeRepo.save(entity);
        Optional<Employee> opt=Optional.ofNullable(employee);
        return opt;
    }
    public Optional<Employee>delete(Long id){
        Optional<Employee> opt=this.employeeRepo.findById(id);
        if (opt.isPresent()){
            this.employeeRepo.deleteById(id);
        }
        return opt;
    }
    public Optional<Employee> addTaskToEmployee(Long idDip, Long idTask){
        Optional<Employee> e = this.employeeRepo.findById(idDip);
		Optional<Task> t = this.taskRepo.findById(idTask);
        if(e.isPresent() && t.isPresent()){
			
			Employee eOggetto = e.get();
			Task tOggetto = t.get();
			Set<Task> t2 = eOggetto.getTasks();
			t2.add(tOggetto);
			employeeRepo.save(eOggetto);
		}else
			System.out.println("EMP-TASK non trovati");
            e = this.employeeRepo.findById(idDip);
        return e;
    }

}
