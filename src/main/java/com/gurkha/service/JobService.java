package com.gurkha.service;

import com.gurkha.dtos.JobDto;
import com.gurkha.entities.Job;

import java.util.List;

public interface JobService {

    JobDto createJob(JobDto job);

    JobDto getJobById(Long id);

    List<JobDto> getAllJobs();

    JobDto updateJob(Long id, JobDto updatedJob);

    void deleteJob(Long id);

}
