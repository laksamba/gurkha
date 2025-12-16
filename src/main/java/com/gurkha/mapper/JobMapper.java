package com.gurkha.mapper;

import com.gurkha.dtos.JobDto;
import com.gurkha.entities.Job;
import com.gurkha.entities.JobType;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public static Job toJob (JobDto dto){
        Job job= new Job();
        job.setPosition(dto.getPosition());
        job.setDescription(dto.getDescription());
        job.setType(dto.getType().equals("REMOTE")?JobType.REMOTE:JobType.ON_SITE);
        job.setPrice(dto.getPrice());
        job.setRequirements(dto.getRequirements());
        return  job;
    }

    public static JobDto toDto (Job job){
        JobDto dto = new JobDto();
        dto.setId(job.getId());
        dto.setPosition(job.getPosition());
        dto.setDescription(job.getDescription());
        dto.setPrice(job.getPrice());
        dto.setType(job.getType().toString());
        dto.setRequirements(job.getRequirements());
        return dto;
    }
}
