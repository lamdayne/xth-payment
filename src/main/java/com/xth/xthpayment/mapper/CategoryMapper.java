package com.xth.xthpayment.mapper;

import com.xth.xthpayment.dto.request.CategoryRequest;
import com.xth.xthpayment.dto.response.CategoryResponse;
import com.xth.xthpayment.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
    void updateCategory(@MappingTarget Category category, CategoryRequest request);
    List<CategoryResponse> toCategoryResponseList(List<Category> categoryList);
    Category toCategory(CategoryResponse response);
}
