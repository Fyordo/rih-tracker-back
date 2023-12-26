package com.rentinhand.rihtracker.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private Set<Task> tasks = new LinkedHashSet<>();

}