package com.ecomm.controller;

import com.ecomm.model.Category;
import com.ecomm.model.Product;
import com.ecomm.service.CategoryService;
import com.ecomm.service.CustomerService;
import com.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.getAllProducts().size());
        model.addAttribute("categoryCount", categoryService.getAllCategories().size());
        model.addAttribute("customerCount", customerService.getAllCustomers().size());
        return "admin/dashboard";
    }

    // --- Category Management ---
    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("newCategory", new Category());
        return "admin/categories";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        try {
            categoryService.saveCategory(category);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Category name already exists.");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = categoryService.deleteCategory(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error",
                    "Cannot delete category: it still has products assigned to it. Remove or reassign those products first.");
        }
        return "redirect:/admin/categories";
    }

    // --- Product Management ---
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/add-product";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        resolveCategory(product);
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String showUpdateProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/add-product";
    }

    /**
     * BUG FIX: This POST endpoint was completely missing. Without it, the update
     * form submitted to /admin/products/add which always created a new product.
     */
    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setProductId(id);
        resolveCategory(product);
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    // --- Customer Management ---
    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customers";
    }

    /**
     * Resolves the nested Category object from the form-submitted categoryId.
     * The HTML select binds a plain Long (category ID) to product.category.categoryId,
     * which creates a detached Category with only the ID set. We need to fetch the
     * managed entity so JPA doesn't throw TransientPropertyValueException.
     */
    private void resolveCategory(Product product) {
        if (product.getCategory() != null && product.getCategory().getCategoryId() != null) {
            Category category = categoryService.getCategoryById(product.getCategory().getCategoryId());
            product.setCategory(category);
        }
    }
}
