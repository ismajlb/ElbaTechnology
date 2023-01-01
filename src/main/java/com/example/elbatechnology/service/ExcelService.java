package com.example.elbatechnology.service;

import com.example.elbatechnology.models.dto.DataHelper;
import com.example.elbatechnology.models.dto.DepartmentHelper;
import com.example.elbatechnology.models.entities.*;
import com.example.elbatechnology.models.responses.UploadExcelFileResponse;
import com.example.elbatechnology.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    private final LeaderDepartmentRepository leaderDepartmentRepository;

    private final ManagerRepository managerRepository;

    @Transactional
    public UploadExcelFileResponse readAndSaveExcelFile(MultipartFile excelFile) {
        try {
            Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Skip the first row (column headers)
            rows.next();

            List<DataHelper> dataHelperList = new ArrayList<>();
            List<DepartmentHelper> departmentHelperList = new ArrayList<>();
            while (rows.hasNext()) {
                Row row = rows.next();

                DataHelper dataHelper = new DataHelper();
                dataHelper.setName(row.getCell(0).getStringCellValue());
                dataHelper.setManager(Objects.nonNull(row.getCell(1)) ? row.getCell(1).getStringCellValue() : null);
                dataHelper.setUsername(row.getCell(2).getStringCellValue());
                dataHelper.setEmail(row.getCell(3).getStringCellValue());
                dataHelper.setDepartment(row.getCell(4).getStringCellValue());
                dataHelper.setPhoneNumber(row.getCell(5).getStringCellValue());
                dataHelper.setAddress(row.getCell(6).getStringCellValue());
                dataHelper.setStartDate(String.valueOf(row.getCell(7).getNumericCellValue()));
                dataHelper.setEndDate(String.valueOf(row.getCell(8).getNumericCellValue()));
                dataHelperList.add(dataHelper);

                if(Objects.nonNull(row.getCell(11))) {
                    DepartmentHelper departmentHelper = new DepartmentHelper();
                    departmentHelper.setName(row.getCell(11).getStringCellValue());
                    departmentHelper.setDepartmentLeader(row.getCell(12).getStringCellValue());
                    departmentHelper.setPhone(row.getCell(13).getStringCellValue());
                    departmentHelperList.add(departmentHelper);
                }
            }

            List<Department> departments = departmentHelperList.stream().map(DepartmentHelper::getDepartment).collect(Collectors.toList());
            departmentRepository.saveAll(departments);

            List<Employee> employees = dataHelperList.stream().map(DataHelper::getEmployee).collect(Collectors.toList());
            employeeRepository.saveAll(employees);

            saveLeadersOfDepartments(departmentHelperList, employees, departments);
            assignDepartmentsToEmployees(dataHelperList, employees, departments);
            List<Manager> managerList = saveManagers(dataHelperList, employees);
            assignMembersToManagers(dataHelperList, managerList, employees);

            log.info("List of employees: {}, and list of departments: {}", dataHelperList.size(), departmentHelperList.size());
            return UploadExcelFileResponse.builder()
                    .isSuccess(Boolean.TRUE)
                    .build();
        } catch (Exception e) {
            return UploadExcelFileResponse.builder()
                    .isSuccess(Boolean.FALSE)
                    .message(e.getMessage())
                    .build();
        }
    }

    private List<Manager> saveManagers(List<DataHelper> dataHelperList, List<Employee> employees){
        List<Manager> managerList = new ArrayList<>();
        Set<String> managersUsernames = dataHelperList.stream().map(DataHelper::getManager).collect(Collectors.toSet());
        for(String mangerUsername : managersUsernames) {
            Employee employee = employees.stream().filter(employee1 -> employee1.getUsername().equals(mangerUsername)).findFirst().orElse(null);
            if(Objects.nonNull(employee)) {
                managerList.add(new Manager(employee));
            }
        }
        managerRepository.saveAll(managerList);

        return managerList;
    }
    private void assignMembersToManagers(List<DataHelper> dataHelperList, List<Manager> managerList, List<Employee> employees) {
        for(DataHelper dataHelper : dataHelperList) {
            Manager manager = managerList.stream()
                    .filter(manager1 -> manager1.getEmployee().getUsername().equals(dataHelper.getManager())).findFirst().orElse(null);
            if(Objects.nonNull(manager)) {
                Employee employee = employees.stream().filter(employee1 -> employee1.getUsername().equals(dataHelper.getUsername())).findFirst().orElse(null);
                if(Objects.nonNull(employee)) {
                    employee.setManager(manager.getEmployee());
                    employeeRepository.save(employee);
                }
            }
        }
    }

    private void assignDepartmentsToEmployees(List<DataHelper> dataHelperList, List<Employee> employees, List<Department> departments) {
        for(DataHelper dataHelper : dataHelperList) {
            Employee employee = employees.stream().filter(employee1 -> employee1.getUsername().equals(dataHelper.getUsername())).findFirst().orElse(null);
            if(Objects.nonNull(employee)) {
                Department department = departments.stream().filter(department1 -> department1.getName().equals(dataHelper.getDepartment())).findFirst().orElse(null);
                if(Objects.isNull(department)) {
                    department = new Department();
                    department.setName(dataHelper.getDepartment());
                    department.setPhone(dataHelper.getPhoneNumber());
                    departmentRepository.save(department);
                    departments.add(department);
                }
                employee.setDepartment(department);
                employeeRepository.save(employee);
            }
        }
    }

    private void saveLeadersOfDepartments(List<DepartmentHelper> departmentHelperList, List<Employee> employees, List<Department> departments) {
        List<LeaderDepartment> leaderDepartmentList = new ArrayList<>();
        for(DepartmentHelper departmentHelper: departmentHelperList) {
            Employee employee = employees.stream().filter(employee1 -> employee1.getUsername().equals(departmentHelper.getDepartmentLeader())).findFirst().orElse(null);
            if(Objects.nonNull(employee)) {
                Department department = departments.stream().filter(department1 -> department1.getName().equals(departmentHelper.getName())).findFirst().orElse(null);
                if(Objects.nonNull(department)) {
                    leaderDepartmentList.add(new LeaderDepartment(department, employee));
                }
            }
        }
        leaderDepartmentRepository.saveAll(leaderDepartmentList);
    }
}
