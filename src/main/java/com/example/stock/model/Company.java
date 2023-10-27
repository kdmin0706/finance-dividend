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

    //Service 레이어 상에서 자신이 원하는 포맷으로 데이터를 바꿀 수 있게 추가
    public static Company fromEntity(CompanyEntity companyEntity) {
        return Company.builder()
                .ticker(companyEntity.getTicker())
                .name(companyEntity.getName())
                .build();
    }
}
