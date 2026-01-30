package com.xth.xthpayment.controller;

import com.xth.xthpayment.dto.request.CreateProductRequest;
import com.xth.xthpayment.dto.request.UpdateProductRequest;
import com.xth.xthpayment.dto.response.ApiResponse;
import com.xth.xthpayment.dto.response.ProductResponse;
import com.xth.xthpayment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.createProduct(request))
                .build();
    }

    @PostMapping("/upload-image/{productId}")
    public ApiResponse<ProductResponse> uploadImageFile(@PathVariable String productId, @RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.uploadImageProduct(productId, file))
                .build();
    }

    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody UpdateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.updateProduct(productId, request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.getProductById(productId))
                .build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<ProductResponse> deleteProduct(@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .message("Product has been deleted")
                .build();
    }

}
