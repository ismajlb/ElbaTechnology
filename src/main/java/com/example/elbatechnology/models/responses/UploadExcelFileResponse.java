package com.example.elbatechnology.models.responses;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadExcelFileResponse {

    Boolean isSuccess;

    String message;
}
