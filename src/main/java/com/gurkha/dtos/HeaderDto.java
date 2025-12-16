package com.gurkha.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HeaderDto {

    private  Long id;


    private String headerLabel;


    private String headerTitle;


    private List<String> paragraphs;

}
