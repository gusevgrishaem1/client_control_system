package com.techls.ofd.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "taxation_system_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Система налогооблажения
public class TaxationSystem {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "pr_archive")
    private Boolean archive;
}
