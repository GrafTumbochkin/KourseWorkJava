package com.example.kourse.Service;

import com.example.kourse.Repository.UserRepository;
import com.example.kourse.Models.User;
import com.example.kourse.Security.SecurityUser; // Импортируем класс SecurityUser
import jakarta.annotation.PostConstruct; // Исправляем импорт для PostConstruct
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebMvc
public class WebSecurityConfig {

    private final UserRepository userRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Используем SecurityUser для загрузки пользователя из базы данных
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new SecurityUser(user); // Возвращаем объект SecurityUser
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Отключаем CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logins", "/register").permitAll() // Страницы регистрации и логина доступны всем
                        .requestMatchers("/products").authenticated() // Страница продуктов доступна авторизованным
                        .requestMatchers("/productsAdmin/**").hasRole("ADMIN") // Страница администрирования доступна только админам
                        .anyRequest().authenticated()  // Все остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .loginPage("/logins")
                        .defaultSuccessUrl("/products", true) // Переход на /products после успешного входа
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()) // Разрешаем выход для всех
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Метод, который создаст пользователя admin при старте приложения, если он еще не существует
    @PostConstruct
    public void initAdminUser() {
        // Проверяем, существует ли пользователь с именем "admin"
        if (!userRepository.existsByUsername("admin")) {
            // Если нет, создаем его
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder().encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}
