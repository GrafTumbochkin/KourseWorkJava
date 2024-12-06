package com.example.kourse.Controllers;

import com.example.kourse.Models.User;
import com.example.kourse.Service.ProductService;
import com.example.kourse.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, ProductService productService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/logins")
    public String login() {
        return "logins";
    }

    @PostMapping("/logins")
    public String performLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return "redirect:/products"; // Успешный вход
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password.");
            return "logins"; // Ошибка при входе
        }
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts()); // Добавляем продукты на страницу
        return "products";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User()); // Создаем пустую модель пользователя
        return "register"; // Возвращаем страницу регистрации
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            if (userService.registerUser(user)) {
                return "redirect:/logins"; // Перенаправляем на страницу логина
            } else {
                model.addAttribute("error", "User already exists");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            e.printStackTrace();
        }
        return "register"; // Если ошибка при регистрации, показываем страницу регистрации
    }
}
