package com.javaguides.expense_tracker_app.mapper;

import com.javaguides.expense_tracker_app.dto.CategoryDto;
import com.javaguides.expense_tracker_app.entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(CategoryDto categoryDto)
    {
        return new Category(
                categoryDto.id(),
                categoryDto.name()
        );
    }

    public static CategoryDto mapToCategoryDto(Category category)
    {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
