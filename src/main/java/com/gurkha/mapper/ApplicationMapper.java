package com.gurkha.mapper;

import com.gurkha.dtos.ApplicationDto;
import com.gurkha.entities.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public static ApplicationDto toDto(Application app) {
        ApplicationDto dto = new ApplicationDto();
        dto.setId(app.getId());
        dto.setPosition(app.getPosition());
        dto.setEmail(app.getEmail());
        dto.setName(app.getName());
        dto.setPhone(app.getPhone());
        dto.setCvName(app.getCvName());
        dto.setCvUrl(app.getCvUrl());
        return dto;
    }

    public static Application toEntity(String name, String url, ApplicationDto dto) {
        Application app = new Application();
        app.setId(dto.getId());
        app.setPosition(dto.getPosition());
        app.setEmail(dto.getEmail());
        app.setName(dto.getName());
        app.setPhone(dto.getPhone());
        app.setCvName(name);
        app.setCvUrl(url);
        return app;
    }
}
