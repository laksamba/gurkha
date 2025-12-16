package com.gurkha.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ValueGenerationType;

@Data
@Entity
@Table(name = "Vacancy")
public class Job {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "position is required")
    private  String position;

    @NotNull(message = "position is required")
    private String price;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "job type is must")
    private JobType type;

    @Column(length = 2000)
    @NotNull(message = "description is must ")
    private String description;

    @ElementCollection
    @NotEmpty(message = "requirement can't be empty")
    private List<String> requirements;


}
