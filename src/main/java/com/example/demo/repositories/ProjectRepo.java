package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.Project;

import jakarta.transaction.Transactional;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
