package com.gurkha.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class JobDto {

    private Long id;
    private String position;
    private String price;
    private String type; // If JobType is enum, you can use String in DTO
    private String description;
    private List<String> requirements;
}