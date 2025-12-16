package com.gurkha.repository;

import com.gurkha.entities.Paragaraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagaraphRepo extends JpaRepository<Paragaraph , Long> {
}
