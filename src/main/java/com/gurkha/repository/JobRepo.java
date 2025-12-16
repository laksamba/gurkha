package com.gurkha.repository;

import com.gurkha.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo  extends JpaRepository<Job, Long> {

}
