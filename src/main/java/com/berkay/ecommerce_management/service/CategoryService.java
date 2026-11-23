package com.berkay.ecommerce_management.service;

import com.berkay.ecommerce_management.requestDto.CategoryRequest;
import com.berkay.ecommerce_management.responseDto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);


}
