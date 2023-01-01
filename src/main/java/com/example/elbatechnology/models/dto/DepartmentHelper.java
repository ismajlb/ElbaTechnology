package com.example.elbatechnology.models.dto;

import com.example.elbatechnology.models.entities.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentHelper {

    private String name;

    private String phone;

    private String departmentLeader;


    public Department getDepartment() {
        return new Department(name, phone);
    }
}
