package com.example.demo.repositories;

import com.example.demo.domains.Task;



import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
