package com.example.delphidevelop.service;

import com.example.delphidevelop.models.responses.EmployeeResponse;
import com.example.delphidevelop.models.responses.UserResponse;
import com.example.delphidevelop.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public List<UserResponse> getActiveAndInactiveUsers() {
        return employeeRepository.getActiveAndInactiveUsers();
    }

    public List<UserResponse> getUsersAscending() {
        return employeeRepository.getUsersAscending();
    }
}
