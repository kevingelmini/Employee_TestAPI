package com.example.demo.domains;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;

import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
@Entity
@Table(name="projects")
public class Project {
    /*Table: projects
Columns:
PROJECT_ID bigint PK 
PROJECT_NAME varchar(25) 
LEADER_ID bigint 
PROJECT_STATUS varchar(1) 
PROJECT_START_DATE date 
PROJECT_END_DATE date */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long project_id;
    @Column(length=25)
    String project_name;

   //Long leader_id;
    @Column(length=1)
    String project_status;

    LocalDate project_start_date;
    LocalDate project_end_date;

    

    @ManyToOne( cascade = { CascadeType.MERGE,CascadeType.PERSIST })
    @JoinColumn(
            name = "leader_id" ,
            //updatable = true, insertable = true,
           // referencedColumnName = "employee_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_projects_leader_id"
                    )
            ) 

    
    @JsonBackReference
    private Employee leader=new Employee();


    @OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    
    
    
    @JsonIgnore
    Set<Task> tasks= new HashSet<>();

    public Project() {
    }

    public Project(Long project_id, String project_name, String project_status, LocalDate project_start_date, LocalDate project_end_date, Employee leader, Set<Task> tasks) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_status = project_status;
        this.project_start_date = project_start_date;
        this.project_end_date = project_end_date;
        this.leader = leader;
        this.tasks = tasks;
    }

    public Long getProject_id() {
        return this.project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return this.project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_status() {
        return this.project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public LocalDate getProject_start_date() {
        return this.project_start_date;
    }

    public void setProject_start_date(LocalDate project_start_date) {
        this.project_start_date = project_start_date;
    }

    public LocalDate getProject_end_date() {
        return this.project_end_date;
    }

    public void setProject_end_date(LocalDate project_end_date) {
        this.project_end_date = project_end_date;
    }

    @JsonIgnore
    public Employee getLeader() {
        return this.leader;
    }

    public Long getLeader_id() {
        return this.leader.employee_id;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }

    @JsonIgnore
    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Project project_id(Long project_id) {
        setProject_id(project_id);
        return this;
    }

    public Project project_name(String project_name) {
        setProject_name(project_name);
        return this;
    }

    public Project project_status(String project_status) {
        setProject_status(project_status);
        return this;
    }

    public Project project_start_date(LocalDate project_start_date) {
        setProject_start_date(project_start_date);
        return this;
    }

    public Project project_end_date(LocalDate project_end_date) {
        setProject_end_date(project_end_date);
        return this;
    }

    public Project leader(Employee leader) {
        setLeader(leader);
        return this;
    }

    public Project tasks(Set<Task> tasks) {
        setTasks(tasks);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(project_id, project.project_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project_id);
    }

    @Override
    public String toString() {
        return "{" +
            " project_id='" + getProject_id() + "'" +
            ", project_name='" + getProject_name() + "'" +
            ", project_status='" + getProject_status() + "'" +
            ", project_start_date='" + getProject_start_date() + "'" +
            ", project_end_date='" + getProject_end_date() + "'" +
            ", leader='" + getLeader() + "'" +
            ", tasks='" + getTasks() + "'" +
            "}";
    }


}
