package com.gurkha.repository;

import com.gurkha.entities.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepo  extends JpaRepository<Header, Long> {

}
