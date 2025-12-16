package com.gurkha.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_header")
@Data
public class Header {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    private String headerLabel;
    private String headerTitle;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paragaraph> paragraphs = new ArrayList<>();
}
