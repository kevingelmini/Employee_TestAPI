package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Project;
import com.example.demo.domains.Task;
import com.example.demo.domains.records.TaskRecord;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.ProjectRepo;
import com.example.demo.repositories.TaskRepository;

@Service
public class TaskService {

    TaskRepository taskRepository;
    EmployeeRepo employeeRepo;
    ProjectRepo projectRepo;

    public TaskService(TaskRepository task, EmployeeRepo employeeRepo, ProjectRepo projectRepo) {
        this.taskRepository = task;
        this.employeeRepo = employeeRepo;
        this.projectRepo = projectRepo;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        Optional<Task> opt = this.taskRepository.findById(id);
        return opt;
    }

    public Optional<Task> save(TaskRecord taskRecord) {

        Optional<Task> opt = Optional.ofNullable(null);
        Optional<Employee> opte = this.employeeRepo.findById(taskRecord.coordinator_id());

        Optional<Project> optp = this.projectRepo.findById(taskRecord.project_id());
        if (opte.isPresent() && optp.isPresent()) {
            Task t = new Task();
            t.setTask_id(taskRecord.task_id());
            t.setCoordinator(opte.get());
            t.setTask_name(taskRecord.task_name());
            t.setProject(optp.get());
            t.setTask_start_date(taskRecord.task_start_date());
            t.setTask_end_date(taskRecord.task_end_date());
            t.setTask_status(taskRecord.task_status());
            Task task = this.taskRepository.save(t);
            opt = Optional.ofNullable(task);

        }
        return opt;
    }

    public Optional<Task> update(Task entity) {
        Task task = this.taskRepository.save(entity);
        Optional<Task> opt = Optional.ofNullable(task);
        return opt;
    }

    public Optional<Task> delete(Long id) {
        Optional<Task> opt = this.taskRepository.findById(id);
        if (opt.isPresent()) {
            this.taskRepository.deleteById(id);
        }
        return opt;
    }
}
