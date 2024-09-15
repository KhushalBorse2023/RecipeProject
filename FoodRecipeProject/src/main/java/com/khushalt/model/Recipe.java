package com.khushalt.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Recipe")
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String title;
    @ManyToOne
    private User user;
    private String image;
    private String description;
    private boolean vegeterisn;
    private LocalDateTime createdAt;
    private List<Long> likes = new ArrayList<>();
}
