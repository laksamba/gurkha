package com.gurkha.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
   
    private  String userName;
    @NotNull(message = "email is must")
    private  String email;

    private  String companyName;

    @NotNull(message = "message is required")
    private String message;

}
