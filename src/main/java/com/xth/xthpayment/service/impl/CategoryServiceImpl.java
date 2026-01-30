package com.xth.xthpayment.service.impl;

import com.xth.xthpayment.dto.request.CategoryRequest;
import com.xth.xthpayment.dto.response.CategoryResponse;
import com.xth.xthpayment.entity.Category;
import com.xth.xthpayment.exception.AppException;
import com.xth.xthpayment.exception.ErrorCode;
import com.xth.xthpayment.mapper.CategoryMapper;
import com.xth.xthpayment.repository.CategoryRepository;
import com.xth.xthpayment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(category, request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)));
    }
}
