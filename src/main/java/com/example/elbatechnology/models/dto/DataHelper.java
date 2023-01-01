package com.example.elbatechnology.models.dto;

import com.example.elbatechnology.models.entities.Employee;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DataHelper {

    private String name;

    private String manager;

    private String username;

    private String email;

    private String department;

    private String phoneNumber;

    private String address;

    private LocalDate startDate;

    private LocalDate endDate;

    public void setStartDate(String startDate) {
        this.startDate = prepareLocalDate(startDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = prepareLocalDate(endDate);
    }

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setName(getName());
        employee.setEmail(getEmail());
        employee.setAddress(getAddress());
        employee.setUsername(getUsername());
        employee.setPhone(getPhoneNumber());
        employee.setStartDate(getStartDate());
        employee.setEndDate(getEndDate());
        employee.setIsActive(!LocalDate.now().isAfter(employee.getEndDate()));
        return employee;
    }

    private LocalDate prepareLocalDate(String localDateString) {
        localDateString = localDateString.replace(".", "");
        localDateString = localDateString.replace("E7", "");
        int year = Integer.parseInt(localDateString.substring(0, 4));
        int month = Integer.parseInt(localDateString.substring(4, 6));
        int day = Integer.parseInt(localDateString.substring(6));

        return LocalDate.of(year, month, day);
    }
}
