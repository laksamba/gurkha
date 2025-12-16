package com.gurkha.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "blog_paragraph")
@Data
public class Paragaraph {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(columnDefinition = "TEXT")
        private String paragraphText;

        @ManyToOne
        @JoinColumn(name = "header_id")
        private Header header;
}
