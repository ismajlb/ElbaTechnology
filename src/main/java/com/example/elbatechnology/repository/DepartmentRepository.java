package com.example.elbatechnology.repository;

import com.example.elbatechnology.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
