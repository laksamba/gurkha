package com.gurkha.mapper;

import com.gurkha.dtos.ParagraphDto;
import com.gurkha.entities.Paragaraph;
import org.springframework.stereotype.Component;

@Component
public class ParagraphMapper {

    public  static ParagraphDto toDto(Paragaraph obj){
        ParagraphDto dto= new ParagraphDto();
        dto.setId(obj.getId());
        dto.setParagraphText(obj.getParagraphText());
        dto.setHeader(obj.getHeader());
        return  dto;
    }


    public  static Paragaraph toEntity(ParagraphDto dto){
        Paragaraph obj= new Paragaraph();
        obj.setId(dto.getId());
        obj.setParagraphText(dto.getParagraphText());
        obj.setHeader(dto.getHeader());
        return  obj;
    }
}
