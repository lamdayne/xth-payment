package com.xth.xthpayment.controller;

import com.xth.xthpayment.dto.request.CategoryRequest;
import com.xth.xthpayment.dto.response.ApiResponse;
import com.xth.xthpayment.dto.response.CategoryResponse;
import com.xth.xthpayment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.createCategory(categoryRequest))
                .build();
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest,
                                                        @PathVariable Integer categoryId
    ) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.updateCategory(categoryId, categoryRequest))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<CategoryResponse>builder()
                .message("Category deleted successfully")
                .build();
    }

    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Integer categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.getCategoryById(categoryId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .data(categoryService.getAllCategories())
                .build();
    }
}
