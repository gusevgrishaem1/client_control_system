package com.techls.ofd.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Клиент
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "inn")
    private String inn;
    @Column(name = "title")
    private String title;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "pr_archive")
    private Boolean archive;
}
