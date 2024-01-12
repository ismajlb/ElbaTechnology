package com.example.delphidevelop;

import com.example.delphidevelop.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelphiDevelop implements CommandLineRunner {

    @Autowired
    private ExcelService excelService;

    public static void main(String[] args) {
        SpringApplication.run(DelphiDevelop.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("System is working fine!");

    }
}
