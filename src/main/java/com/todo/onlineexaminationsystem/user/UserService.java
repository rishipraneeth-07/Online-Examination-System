package com.todo.onlineexaminationsystem.user;

import java.util.Optional;

public interface UserService {
    User createUser(User user);

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);
}
