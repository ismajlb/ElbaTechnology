package com.example.elbatechnology;

import com.example.elbatechnology.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElbaTechnologyApplication implements CommandLineRunner {

    @Autowired
    private ExcelService excelService;

    public static void main(String[] args) {
        SpringApplication.run(ElbaTechnologyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("System is working fine!");

    }
}
