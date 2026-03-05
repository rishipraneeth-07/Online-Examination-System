package com.todo.onlineexaminationsystem.Config;

import com.todo.onlineexaminationsystem.user.Role;
import com.todo.onlineexaminationsystem.user.RoleRepo;
import com.todo.onlineexaminationsystem.user.User;
import com.todo.onlineexaminationsystem.user.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DataInitializer {
    @Bean
    CommandLineRunner initData(
            RoleRepo roleRepository,
            UserRepo userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));

            Role teacherRole = roleRepository.findByName("TEACHER")
                    .orElseGet(() -> roleRepository.save(new Role(null, "TEACHER")));

            Role studentRole = roleRepository.findByName("STUDENT")
                    .orElseGet(() -> roleRepository.save(new Role(null, "STUDENT")));

            if (userRepository.findByEmail("admin@test.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@test.com");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setEnabled(true);
                admin.setRole(adminRole);

                userRepository.save(admin);
            }

            if (userRepository.findByEmail("teacher@test.com").isEmpty()) {
                User teacher = new User();
                teacher.setEmail("teacher@test.com");
                teacher.setPassword(passwordEncoder.encode("password"));
                teacher.setEnabled(true);
                teacher.setRole(teacherRole);

                userRepository.save(teacher);
            }

            if (userRepository.findByEmail("student@test.com").isEmpty()) {
                User student = new User();
                student.setEmail("student@test.com");
                student.setPassword(passwordEncoder.encode("password"));
                student.setEnabled(true);
                student.setRole(studentRole);

                userRepository.save(student);
            }
        };
    }
}
