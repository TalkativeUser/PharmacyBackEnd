package com.ecommerce.pharmacy.service.Impl;

import com.ecommerce.pharmacy.DAO.CategoryRepository;
import com.ecommerce.pharmacy.Entity.Category;
import com.ecommerce.pharmacy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category findCategoryById(Long id)throws Exception{
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new Exception("Category not found");
        }
        return category.get();
    }


    @Override
    public Page<Category> findCategoriesWithPagination(int offset) {
        final int pageSize = 10;
        return categoryRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Category> findCategoriesWithPaginationAndSorting(int offset,String field) {
        final int pageSize = 10;
        return categoryRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }


                    /**              ADMIN SERVICE            */

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
    @Override
    public Category updateCategory(Long id, Category category) throws Exception {
        Optional<Category> validateCategory = categoryRepository.findById(id);
        if (!validateCategory.isPresent()) {
            throw new Exception("Category not found");
        }
        validateCategory.get().setTitle(category.getTitle());
        validateCategory.get().setImgURL(category.getImgURL());
        return validateCategory.get();
    }

    @Override
    public void deleteCategoryById(Long id) throws Exception {
        Optional<Category> validateCategory = categoryRepository.findById(id);
        if (!validateCategory.isPresent()) {
            throw new Exception("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
