package com.techls.ofd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "cash_register_model")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CashRegisterModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
}
