package com.example.delphidevelop.repository;

import com.example.delphidevelop.models.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
