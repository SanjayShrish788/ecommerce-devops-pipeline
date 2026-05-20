package com.ecomm;

import com.ecomm.controller.CustomerController;
import com.ecomm.model.Category;
import com.ecomm.repository.CategoryRepository;
import com.ecomm.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class EcommerceApplicationTests {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
        // Test 1: Controller Smoke Test
        assertNotNull(customerController, "CustomerController should have been injected");
    }

    @Test
    void testCategoryService() {
        // Test 2: Service Layer Test with Mock Repository
        Category mockCategory = new Category();
        mockCategory.setCategoryId(1L);
        mockCategory.setName("Electronics");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
        when(categoryRepository.save(mockCategory)).thenReturn(mockCategory);

        Category savedCategory = categoryService.saveCategory(mockCategory);
        assertNotNull(savedCategory, "Saved category should not be null");
        assertEquals("Electronics", savedCategory.getName(), "Category name should match");
    }

    @Test
    void testModelCreation() {
        // Test 3: Model instantiation and logic test
        Category cat = new Category();
        cat.setName("Books");

        assertEquals("Books", cat.getName(), "Model setters/getters should work");
    }

}
