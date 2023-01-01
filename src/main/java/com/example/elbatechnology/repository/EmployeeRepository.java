package com.example.elbatechnology.repository;

import com.example.elbatechnology.models.entities.Employee;
import com.example.elbatechnology.models.responses.EmployeeResponse;
import com.example.elbatechnology.models.responses.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select new com.example.elbatechnology.models.responses.EmployeeResponse(e.id, e.name, e.username, e.email, e.phone, e.address) from Employee e " +
            "where e.id not in (select m.employee.id from Manager m)")
    List<EmployeeResponse> getEmployees();

    @Query("select new com.example.elbatechnology.models.responses.UserResponse(e.id, e.name, e.username, e.email, e.phone, e.address, e.isActive) from Employee e")
    List<UserResponse> getActiveAndInactiveUsers();

    @Query("select new com.example.elbatechnology.models.responses.UserResponse(e.id, e.name, e.username, e.email, e.phone, e.address, e.isActive) from Employee e order by e.name asc")
    List<UserResponse> getUsersAscending();
}
