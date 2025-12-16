package com.gurkha.serviceImpl;

import com.gurkha.dtos.JobDto;
import com.gurkha.entities.Job;
import com.gurkha.entities.JobType;
import com.gurkha.exception.BadRequestException;
import com.gurkha.exception.ResourceNotFoundException;
import com.gurkha.mapper.JobMapper;
import com.gurkha.repository.JobRepo;
import com.gurkha.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl  implements JobService {

    private  final JobRepo jobRepository;

    @Override
    public JobDto createJob(JobDto dto) {
        return  JobMapper.toDto(jobRepository.save(JobMapper.toJob(dto)));
    }

    @Override
    public JobDto getJobById(Long id) {
        return JobMapper.toDto(jobRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Job with id " + id + " not found")));
    }

    @Override
    public List<JobDto> getAllJobs() {
        return jobRepository.findAll().stream().map(JobMapper::toDto).toList();
    }
    @Override
    public JobDto updateJob(Long id, JobDto updatedJob) {
        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job with id " + id + " not found"));

        existing.setPosition(updatedJob.getPosition());
        existing.setPrice(updatedJob.getPrice());
        existing.setType(updatedJob.getType().toUpperCase()=="remote"? JobType.REMOTE:JobType.ON_SITE);
        existing.setDescription(updatedJob.getDescription());
        existing.setRequirements(updatedJob.getRequirements());

        return  JobMapper.toDto(jobRepository.save(existing));
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job with id " + id + " not found"));
        jobRepository.delete(job);
    }
}

