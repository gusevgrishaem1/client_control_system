package com.techls.ofd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ofd")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Оператор фискальных данных
public class OFD {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "inn")
    private String inn;
}
