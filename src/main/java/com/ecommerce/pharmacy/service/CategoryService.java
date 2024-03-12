package com.ecommerce.pharmacy.service;

import com.ecommerce.pharmacy.Entity.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    public Category findCategoryById(Long id)throws Exception;

    public Page<Category> findCategoriesWithPagination(int offset);
    public Page<Category> findCategoriesWithPaginationAndSorting(int offset,String field);

    public void createCategory(Category category);
    public Category updateCategory (Long id, Category category)throws Exception;
    public void deleteCategoryById(Long id)throws Exception;
}
