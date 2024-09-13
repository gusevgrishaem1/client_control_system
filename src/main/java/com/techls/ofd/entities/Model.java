package com.techls.ofd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "model_data")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Модель кассы
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "pr_archive")
    private Boolean archive;
}
