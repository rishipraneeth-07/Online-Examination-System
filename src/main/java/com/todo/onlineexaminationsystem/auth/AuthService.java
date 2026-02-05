package com.todo.onlineexaminationsystem.auth;

import com.todo.onlineexaminationsystem.dto.LoginRequest;
import com.todo.onlineexaminationsystem.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
