package com.techls.ofd.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientFilterDTO {
    private Long id;
    private String inn;
    private String title;
    private String telephone;
    private int page = 0;
    private int size = 30;
    private String sortColumn = "id";
    private String sortDirection = "asc";
}
