# Online Examination System (Backend)

A secure, role-based Online Examination System built using Spring Boot.
The system allows teachers to create and manage exams, students to attempt them within scheduled time windows, and automatically evaluates results using a scoring engine.

## Features Implemented
- JWT-based stateless authentication
- BCrypt password encryption
- Role-Based Access Control (RBAC):
ADMIN,
TEACHER,
STUDENT,
- Secure endpoint protection using Spring Security
- Custom JWT filter integration
- Global exception handling with standardized error responses

# Application Flow – Online Examination System

## User & Authentication Flow
- The system supports three roles: ADMIN, TEACHER, STUDENT.
- Users authenticate using email and password.
- On successful login, a JWT token is issued.
- All subsequent requests require the JWT token.
- Access to APIs is restricted based on user roles.

## User Management Flow (Admin)
- Admin creates user accounts.
- Admin assigns roles (TEACHER or STUDENT).
- Only enabled users can access the system.
- Admin has access to all system data.

