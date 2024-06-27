package com.javaguides.expense_tracker_app.service;

import com.javaguides.expense_tracker_app.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto creatCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    void deleteCategory(Long categoryId);
}
