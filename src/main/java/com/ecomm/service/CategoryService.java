package com.ecomm.service;

import com.ecomm.model.Category;
import com.ecomm.repository.CategoryRepository;
import com.ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Deletes a category only if no products are linked to it.
     * Returns true on success, false if the category has dependent products.
     */
    public boolean deleteCategory(Long id) {
        long productCount = productRepository.countByCategoryCategoryId(id);
        if (productCount > 0) {
            return false; // Cannot delete: has dependent products
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
