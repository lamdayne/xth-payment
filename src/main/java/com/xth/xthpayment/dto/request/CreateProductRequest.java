package com.xth.xthpayment.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String name;

    private Double basePrice;

    private Double salePrice;

    private Long stock;

    private String description;

    private int status;

    private int categoryId;
}
