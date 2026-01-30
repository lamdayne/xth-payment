package com.xth.xthpayment.mapper;

import com.xth.xthpayment.dto.request.CreateProductRequest;
import com.xth.xthpayment.dto.request.UpdateProductRequest;
import com.xth.xthpayment.dto.response.ProductResponse;
import com.xth.xthpayment.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(CreateProductRequest request);
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, UpdateProductRequest request);
    List<ProductResponse> toProductResponseList(List<Product> productList);
}
