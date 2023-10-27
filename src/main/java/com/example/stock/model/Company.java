package com.example.stock.model;

import com.example.stock.persist.entity.CompanyEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String ticker;
    private String name;
}
