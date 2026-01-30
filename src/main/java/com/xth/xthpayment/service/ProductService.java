package com.xth.xthpayment.service;

import com.xth.xthpayment.dto.request.CreateProductRequest;
import com.xth.xthpayment.dto.request.UpdateProductRequest;
import com.xth.xthpayment.dto.response.ProductResponse;
import com.xth.xthpayment.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse updateProduct(String id, UpdateProductRequest request);
    ProductResponse getProductById(String id);
    List<ProductResponse> getAllProducts();
    void deleteProductById(String id);
    ProductResponse uploadImageProduct(String id, MultipartFile file) throws IOException;
}
