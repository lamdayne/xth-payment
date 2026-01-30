package com.xth.xthpayment.service.impl;

import com.xth.xthpayment.dto.request.CreateProductRequest;
import com.xth.xthpayment.dto.request.UpdateProductRequest;
import com.xth.xthpayment.dto.response.ProductResponse;
import com.xth.xthpayment.entity.Category;
import com.xth.xthpayment.entity.Product;
import com.xth.xthpayment.exception.AppException;
import com.xth.xthpayment.exception.ErrorCode;
import com.xth.xthpayment.mapper.CategoryMapper;
import com.xth.xthpayment.mapper.ProductMapper;
import com.xth.xthpayment.repository.ProductRepository;
import com.xth.xthpayment.service.CategoryService;
import com.xth.xthpayment.service.ProductService;
import com.xth.xthpayment.service.UploadImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final UploadImageFileService uploadImageFileService;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Category category = categoryMapper.toCategory(categoryService.getCategoryById(request.getCategoryId()));
        Product product = productMapper.toProduct(request);
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        Category category = categoryMapper.toCategory(categoryService.getCategoryById(request.getCategoryId()));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProduct(product, request);
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProductById(String id) {
        return productMapper.toProductResponse(productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND))
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productMapper.toProductResponseList(productRepository.findAll());
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse uploadImageProduct(String id, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setThumbnail(uploadImageFileService.uploadImageFile(file));
        return productMapper.toProductResponse(productRepository.save(product));
    }
}
