package com.xth.xthpayment.service;

import com.xth.xthpayment.dto.request.CategoryRequest;
import com.xth.xthpayment.dto.response.CategoryResponse;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Integer id, CategoryRequest request);
    void deleteCategory(Integer id);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Integer id);
}
