package com.gurkha.controller;

import com.gurkha.dtos.JobDto;
import com.gurkha.entities.Job;
import com.gurkha.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAllJobs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long id, @RequestBody JobDto job) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.updateJob(id,job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Job deleted successfully");
    }
}
