package com.gurkha.dtos;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ApplicationDto {

    private Long id;
    private String position;
    private String name;
    private String phone;
    private String email;
    private  String cvName;
    private  String cvUrl;
    private MultipartFile cv;

}
