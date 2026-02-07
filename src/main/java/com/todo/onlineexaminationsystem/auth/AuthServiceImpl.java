package com.todo.onlineexaminationsystem.auth;

import com.todo.onlineexaminationsystem.dto.LoginRequest;
import com.todo.onlineexaminationsystem.dto.LoginResponse;
import com.todo.onlineexaminationsystem.user.User;
import com.todo.onlineexaminationsystem.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserService userService;
    private final PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!user.isEnabled()) {
            throw new RuntimeException("User account is disabled");
        }
        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().getName()
        );
    }
}
