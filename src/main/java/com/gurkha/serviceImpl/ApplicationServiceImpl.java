package com.gurkha.serviceImpl;

import com.gurkha.dtos.ApplicationDto;
import com.gurkha.entities.Application;
import com.gurkha.exception.BadRequestException;
import com.gurkha.exception.ResourceNotFoundException;
import com.gurkha.fileHadling.FilePutGet;
import com.gurkha.mapper.ApplicationMapper;
import com.gurkha.repository.ApplicationRepo;
import com.gurkha.service.ApplicationService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl  implements ApplicationService {

    private final   ApplicationRepo applicationRepository;

    
    @Override
    public ApplicationDto createApplication(ApplicationDto dto) {
        
        MultipartFile cvFile = dto.getCv();

        if (cvFile == null || cvFile.isEmpty()) {
            throw new BadRequestException("CV file is required");
        }
        // Save file using your file service
        String[] savedData = filePutGet.saveFile(cvFile,"cv");

        String fileName = savedData[0];
        String fullPath = savedData[2];

        ApplicationDto tempHolder= new ApplicationDto();
        tempHolder.setCvName(fileName);
        tempHolder.setCvUrl(fullPath);

        return ApplicationMapper.toDto(applicationRepository.save(ApplicationMapper.toEntity(fileName,fullPath,dto)));
    }

    @Override
    public List<ApplicationDto> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(ApplicationMapper::toDto)
                .toList();
    }


    @Override
    public ApplicationDto getById(Long id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));
        return ApplicationMapper.toDto(app);
    }

    @Override
    public void deleteApplication(Long id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));
        applicationRepository.delete(app);
    }
}

