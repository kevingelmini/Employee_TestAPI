package com.example.demo.domains;
/*
    TASK_ID	bigint PK
	TASK_NAME	varchar(25)
	TASK_STATUS	varchar(1)
	PROJECT_ID	bigint
	COORDINATOR_ID	bigint
	TASK_START_DATE	date #https://www.baeldung.com/hibernate-date-time
	TASK_END_DATE	date
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task{

        @Id
        Long task_id;

        @Column(length = 25)
        String task_name;

        @Column(length = 1)
        String task_status;

        
        
        // Long project_id;

        @ManyToMany(mappedBy = "tasks", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
        // Non serve perché sono simmetriche
        // @JoinTable (
        // name = "emp_tasks",
        // joinColumns = @JoinColumn(name = "task_id", nullable = false),
        // inverseJoinColumns = @JoinColumn(name = "employee_id", nullable = false),

        // foreignKey = @ForeignKey(
        // name = "fk_tasks_employes_id"
        // )
        // )
        @JsonIgnore
        Set<Employee> employees=new HashSet<>();

        LocalDate task_start_date;

        LocalDate task_end_date;

        @ManyToOne( cascade =  {CascadeType.MERGE,CascadeType.PERSIST})// {CascadeType.MERGE,CascadeType.PERSIST})

        @JoinColumn(
                name = "coordinator_id", 
                referencedColumnName = "employee_id",
                nullable = false, 
                foreignKey = @ForeignKey(name = "fk_projects_coordinator_id"))
       
        @JsonIgnore       
        
        private Employee coordinator= new Employee();


        @ManyToOne( cascade = { CascadeType.MERGE,CascadeType.PERSIST })
        
        @JoinColumn(
                name = "project_id",
                referencedColumnName = "project_id",
                nullable = false, 
                foreignKey = @ForeignKey(name = "fk_tasks_project_id"))
        @JsonIgnore
       
        private Project project=new Project();


        public Task() {
        }

        public Task(Long task_id, String task_name, String task_status, Project project, Set<Employee> employees, LocalDate task_start_date, LocalDate task_end_date, Employee coordinator) {
                this.task_id = task_id;
                this.task_name = task_name;
                this.task_status = task_status;
                this.project = project;
                this.employees = employees;
                this.task_start_date = task_start_date;
                this.task_end_date = task_end_date;
                this.coordinator = coordinator;
        }

        public Long getTask_id() {
                return this.task_id;
        }

        public void setTask_id(Long task_id) {
                this.task_id = task_id;
        }

        public String getTask_name() {
                return this.task_name;
        }

        public void setTask_name(String task_name) {
                this.task_name = task_name;
        }

        public String getTask_status() {
                return this.task_status;
        }

        public void setTask_status(String task_status) {
                this.task_status = task_status;
        }

        public Project getProject() {
                return this.project;
        }
        public Long getProject_id() {
                return this.project.project_id;
        }
        public void setProject_id(Long project_id) {
                this.project.setProject_id(project_id);
        }

        public Long getCoordinator_id() {
                return this.coordinator.employee_id;
        }
        public void setCoordinator_id(Long coordinator_id) {
                this.coordinator.setEmployee_id(coordinator_id);
        }

        public void setProject(Project project) {
                this.project = project;
        }

        public Set<Employee> getEmployees() {
                return this.employees;
        }

        public void setEmployees(Set<Employee> employees) {
                this.employees = employees;
        }

        public LocalDate getTask_start_date() {
                return this.task_start_date;
        }

        public void setTask_start_date(LocalDate task_start_date) {
                this.task_start_date = task_start_date;
        }

        public LocalDate getTask_end_date() {
                return this.task_end_date;
        }

        public void setTask_end_date(LocalDate task_end_date) {
                this.task_end_date = task_end_date;
        }

        public Employee getCoordinator() {
                return this.coordinator;
        }

        public void setCoordinator(Employee coordinator) {
                this.coordinator = coordinator;
        }

        public Task task_id(Long task_id) {
                setTask_id(task_id);
                return this;
        }

        public Task task_name(String task_name) {
                setTask_name(task_name);
                return this;
        }

        public Task task_status(String task_status) {
                setTask_status(task_status);
                return this;
        }

        public Task project(Project project) {
                setProject(project);
                return this;
        }

        public Task employees(Set<Employee> employees) {
                setEmployees(employees);
                return this;
        }

        public Task task_start_date(LocalDate task_start_date) {
                setTask_start_date(task_start_date);
                return this;
        }

        public Task task_end_date(LocalDate task_end_date) {
                setTask_end_date(task_end_date);
                return this;
        }

        public Task coordinator(Employee coordinator) {
                setCoordinator(coordinator);
                return this;
        }

        @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(task_id, task.task_id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(task_id);
        }

        @Override
        public String toString() {
                return "{" +
                        " task_id='" + getTask_id() + "'" +
                        ", task_name='" + getTask_name() + "'" +
                        ", task_status='" + getTask_status() + "'" +
                        ", project='" + getProject() + "'" +
                        ", employees='" + getEmployees() + "'" +
                        ", task_start_date='" + getTask_start_date() + "'" +
                        ", task_end_date='" + getTask_end_date() + "'" +
                        ", coordinator='" + getCoordinator() + "'" +
                        "}";
        }

}
