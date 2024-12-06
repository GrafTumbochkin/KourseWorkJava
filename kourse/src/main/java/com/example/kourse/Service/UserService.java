package com.example.kourse.Service;

import com.example.kourse.Models.User;
import com.example.kourse.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Регистрация нового пользователя
    public boolean registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false; // Пользователь с таким именем уже существует
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Кодируем пароль
        user.setRole("CLIENT"); // Устанавливаем роль "CLIENT"
        userRepository.save(user); // Сохраняем пользователя в базе данных
        return true;
    }
}
