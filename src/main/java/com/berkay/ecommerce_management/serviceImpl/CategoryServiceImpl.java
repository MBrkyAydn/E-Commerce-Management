package com.berkay.ecommerce_management.serviceImpl;

import com.berkay.ecommerce_management.entity.Category;
import com.berkay.ecommerce_management.exception.DuplicateResourceException;
import com.berkay.ecommerce_management.exception.ResourceNotFoundException;
import com.berkay.ecommerce_management.repository.CategoryRepository;
import com.berkay.ecommerce_management.requestDto.CategoryRequest;
import com.berkay.ecommerce_management.responseDto.CategoryResponse;
import com.berkay.ecommerce_management.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.name().trim())) {
            throw new DuplicateResourceException("Category with name '" + request.name() + "' already exists");
        }

        Category category = new Category();
        category.setName(request.name().trim());
        category.setDescription(request.description() != null ? request.description().trim() : null);
        category.setActive(true);

        Category savedCategory = categoryRepository.save(category);
        return mapToResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return mapToResponse(category);
    }

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive()
        );
    }
}