package com.gurkha.controller;
import com.gurkha.dtos.ApplicationDto;
import com.gurkha.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Data
@RestController
@RequestMapping("/api/app")
@RequiredArgsConstructor
public class ApplicationController {

     private final ApplicationService applicationService;

        @PostMapping
        public ResponseEntity<ApplicationDto> create(@ModelAttribute ApplicationDto dto) {
            ApplicationDto created = applicationService.createApplication(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<ApplicationDto>> getAll() {
            return ResponseEntity.ok(applicationService.getAllApplications());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApplicationDto> getById(@PathVariable Long id) {
            return ResponseEntity.ok(applicationService.getById(id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable Long id) {
            applicationService.deleteApplication(id);
            return ResponseEntity.ok("Application deleted successfully");
        }
    }
