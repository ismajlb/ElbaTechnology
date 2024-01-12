package com.example.delphidevelop.models.responses;


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
