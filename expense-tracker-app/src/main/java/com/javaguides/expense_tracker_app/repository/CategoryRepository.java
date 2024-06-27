package com.javaguides.expense_tracker_app.repository;

import com.javaguides.expense_tracker_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Spring data jpa provides implementation for this interface
    //crud methods to perform crud database operations on category entity
    //spring data jpa provides transaction for all the crud methods
}
