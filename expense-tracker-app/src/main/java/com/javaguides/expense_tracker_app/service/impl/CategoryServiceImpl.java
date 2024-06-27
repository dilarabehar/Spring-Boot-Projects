package com.javaguides.expense_tracker_app.service.impl;

import com.javaguides.expense_tracker_app.dto.CategoryDto;
import com.javaguides.expense_tracker_app.entity.Category;
import com.javaguides.expense_tracker_app.exceptions.ResourceNotFoundException;
import com.javaguides.expense_tracker_app.mapper.CategoryMapper;
import com.javaguides.expense_tracker_app.repository.CategoryRepository;
import com.javaguides.expense_tracker_app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;


    @Override
    public CategoryDto creatCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categoryDtoList = categoryRepository.findAll();
        return categoryDtoList.stream().map((category) -> CategoryMapper.mapToCategoryDto(category)).collect(Collectors.toList());

    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found with id" + categoryId));
        category.setName(categoryDto.name());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(updatedCategory);

    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));
        categoryRepository.delete(category);
    }
}
