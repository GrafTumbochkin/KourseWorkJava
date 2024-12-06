package com.example.kourse.Controllers;

import com.example.kourse.Models.Product;
import com.example.kourse.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productsAdmin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "productsAdmin";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/productsAdmin";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return "redirect:/productsAdmin";
    }

    // Метод для удаления продукта
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/productsAdmin"; // Перенаправление на страницу управления продуктами после удаления
    }
}
