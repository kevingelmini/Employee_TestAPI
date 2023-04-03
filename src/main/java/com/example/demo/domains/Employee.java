package com.example.demo.domains;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    /*
     * EMPLOYEE_ID bigint PK
     * FIRST_NAME varchar(20)
     * LAST_NAME varchar(25)
     * EMAIL varchar(45)
     * SALARY decimal(18,4)
     * TOTAL_WORK_PERCENTAGE double
     * MEMBERSHIP_COUNT bigint
     * DEPARTMENT_ID bigint
     */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long employee_id;
    @Column(length = 20)
    String first_name;

    @Column(length = 20)
    String last_name;

    @Column(length = 45)
    String email;
    @Column(precision = 18, scale = 4)
    BigDecimal salary;

    double total_work_percentage;

    Long membership_count;
    Long department_id;

  
    @OneToMany(mappedBy = "leader", 
        
        cascade = { CascadeType.MERGE,CascadeType.PERSIST }, 
        orphanRemoval = true)
    //@JsonIgnore
    @JsonManagedReference
    Set<Project> projects= new HashSet<>();

    @OneToMany(mappedBy = "coordinator", cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    @JsonIgnore
    
    Set<Task> coordinator_tasks= new HashSet<>();
    
    //LAZY: rallenta le richieste. Da la possiboità di ottimizzare la gestione della memoria. JSON non fa richieste.
    //EAGER: carica preventivamente i dati
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
      name = "emp_tasks", 
      joinColumns = @JoinColumn(name = "employee_id", nullable = false), 
      inverseJoinColumns = @JoinColumn(name = "task_id", nullable = false),
      foreignKey = @ForeignKey(
        name = "fk_employes_tasks_id"
        )
      
      )
      //mapped by dall'altra
    @JsonIgnore
    Set<Task> tasks;

    public Employee() {
    }


    
    public Employee(Long employee_id, String first_name, String last_name, String email, BigDecimal salary, double total_work_percentage, Long membership_count, Long department_id, Set<Project> projects, Set<Task> coordinator_tasks, Set<Task> tasks) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.salary = salary;
        this.total_work_percentage = total_work_percentage;
        this.membership_count = membership_count;
        this.department_id = department_id;
        this.projects = projects;
        this.coordinator_tasks = coordinator_tasks;
        this.tasks = tasks;
    }

    public Long getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public double getTotal_work_percentage() {
        return this.total_work_percentage;
    }

    public void setTotal_work_percentage(double total_work_percentage) {
        this.total_work_percentage = total_work_percentage;
    }

    public Long getMembership_count() {
        return this.membership_count;
    }

    public void setMembership_count(Long membership_count) {
        this.membership_count = membership_count;
    }

    public Long getDepartment_id() {
        return this.department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public List<Long> getProjects_project_id() {
        return this.projects.stream().map(e -> e.getProject_id()).collect(Collectors.toList());
    }
    @JsonIgnore
    public Set<Project> getProjects() {
        return this.projects;
    }


    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Task> getCoordinator_tasks() {
        return this.coordinator_tasks;
    }

    public void setCoordinator_tasks(Set<Task> coordinator_tasks) {
        this.coordinator_tasks = coordinator_tasks;
    }
    @JsonIgnore
    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Employee employee_id(Long employee_id) {
        setEmployee_id(employee_id);
        return this;
    }

    public Employee first_name(String first_name) {
        setFirst_name(first_name);
        return this;
    }

    public Employee last_name(String last_name) {
        setLast_name(last_name);
        return this;
    }

    public Employee email(String email) {
        setEmail(email);
        return this;
    }

    public Employee salary(BigDecimal salary) {
        setSalary(salary);
        return this;
    }

    public Employee total_work_percentage(double total_work_percentage) {
        setTotal_work_percentage(total_work_percentage);
        return this;
    }

    public Employee membership_count(Long membership_count) {
        setMembership_count(membership_count);
        return this;
    }

    public Employee department_id(Long department_id) {
        setDepartment_id(department_id);
        return this;
    }

    public Employee projects(Set<Project> projects) {
        setProjects(projects);
        return this;
    }

    public Employee coordinator_tasks(Set<Task> coordinator_tasks) {
        setCoordinator_tasks(coordinator_tasks);
        return this;
    }

    public Employee tasks(Set<Task> tasks) {
        setTasks(tasks);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(employee_id, employee.employee_id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id);
    }

    @Override
    public String toString() {
        return "{" +
            " employee_id='" + getEmployee_id() + "'" +
            ", first_name='" + getFirst_name() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", email='" + getEmail() + "'" +
            ", salary='" + getSalary() + "'" +
            ", total_work_percentage='" + getTotal_work_percentage() + "'" +
            ", membership_count='" + getMembership_count() + "'" +
            ", department_id='" + getDepartment_id() + "'" +
            ", projects='" + getProjects() + "'" +
            ", coordinator_tasks='" + getCoordinator_tasks() + "'" +
            ", tasks='" + getTasks() + "'" +
            "}";
    }


}
