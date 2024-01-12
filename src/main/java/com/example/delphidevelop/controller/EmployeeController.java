package com.example.delphidevelop.controller;


import com.example.delphidevelop.models.responses.EmployeeResponse;
import com.example.delphidevelop.models.responses.UserResponse;
import com.example.delphidevelop.service.EmployeeService;
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
