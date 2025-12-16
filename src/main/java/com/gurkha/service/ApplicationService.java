package com.gurkha.service;

import com.gurkha.dtos.ApplicationDto;
import java.util.List;

public interface ApplicationService {

    ApplicationDto createApplication(ApplicationDto dto);

    List<ApplicationDto> getAllApplications();

    ApplicationDto getById(Long id);

    void deleteApplication(Long id);
}
