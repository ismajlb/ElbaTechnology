package com.example.elbatechnology.controller;

import com.example.elbatechnology.models.entities.Employee;
import com.example.elbatechnology.models.responses.UploadExcelFileResponse;
import com.example.elbatechnology.repository.EmployeeRepository;
import com.example.elbatechnology.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("excel")
public class ExcelController {

    private final ExcelService excelService;

//    private final EmployeeRepository employeeRepository;

    @PostMapping("/import")
    public ResponseEntity<UploadExcelFileResponse> uploadExcelFile(@RequestParam("excelFile") MultipartFile excelFile){
        log.info("File name: {}", excelFile.getOriginalFilename());

        UploadExcelFileResponse uploadExcelFileResponse = excelService.readAndSaveExcelFile(excelFile);
        HttpStatus httpStatus = HttpStatus.CREATED;
        if(uploadExcelFileResponse.getIsSuccess().equals(Boolean.FALSE)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(httpStatus).body(uploadExcelFileResponse);
    }



}
