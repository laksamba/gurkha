package com.gurkha.dtos;

import lombok.Data;

@Data
public class ContactDto {
    
    private Long id;
    private String userName;
    private String email;
    private String companyName;
    private String message;
}