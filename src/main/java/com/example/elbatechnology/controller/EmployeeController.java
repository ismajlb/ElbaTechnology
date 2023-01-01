package com.example.elbatechnology.controller;


import com.example.elbatechnology.models.entities.Employee;
import com.example.elbatechnology.models.responses.EmployeeResponse;
import com.example.elbatechnology.models.responses.UserResponse;
import com.example.elbatechnology.repository.EmployeeRepository;
import com.example.elbatechnology.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees", produces="application/json")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();

        return ResponseEntity.ok(employeeResponses);
    }

    @GetMapping("/active-inactive")
    public ResponseEntity<List<UserResponse>> getActiveAndInactiveUsers() {
        List<UserResponse> userResponses = employeeService.getActiveAndInactiveUsers();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/ascending")
    public ResponseEntity<List<UserResponse>> getUsersAscending() {
        List<UserResponse> userResponses = employeeService.getUsersAscending();

        return ResponseEntity.ok(userResponses);
    }

}
