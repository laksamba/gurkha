package com.gurkha.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String position;
    private  String name;
    private  String  email;
    private  String phone;
    private   String cvUrl;

    @Column(unique = true)
    private   String cvName;


}
