package com.example.demo.domains.records;

import java.time.LocalDate;
// dto --> data transfer object
// tupla  --> 
public record TaskRecord(
        Long task_id,
        String task_name,
        String task_status,
        LocalDate task_start_date,
        LocalDate task_end_date,
        Long project_id,
        Long coordinator_id) {
}