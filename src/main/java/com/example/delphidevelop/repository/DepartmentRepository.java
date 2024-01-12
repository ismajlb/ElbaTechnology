package com.example.delphidevelop.repository;

import com.example.delphidevelop.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
