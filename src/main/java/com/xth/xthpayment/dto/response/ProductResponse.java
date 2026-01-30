package com.xth.xthpayment.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private Long basePrice;
    private Long salePrice;
    private Long stock;
    private String thumbnail;
    private String description;
    private int status;
}
