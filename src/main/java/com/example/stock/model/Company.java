package com.example.stock.model;

import com.example.stock.persist.entity.CompanyEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    private String ticker;
    private String name;
}
