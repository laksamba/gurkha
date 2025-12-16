package com.gurkha.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Blog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true)
    @NotBlank(message = "image name not set")
    private  String imageName;
    @NotBlank(message = "image url not set")
    private  String imageUrl;
    @NotBlank(message = "title not set")
    private  String  title;
    @NotBlank(message = "author not set")
    private  String author;

    private String description;

    @CreationTimestamp
    private LocalDateTime publishedOn;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Header> headers = new ArrayList<>();
}
