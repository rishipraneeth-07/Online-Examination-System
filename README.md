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

## Exam Creation Flow (Teacher)
- Teacher logs in and creates an exam.
- Each exam includes:
Title
Description
Duration (in minutes)
Start time
End time
- Validations enforced
- Only the exam creator can manage their exam.

## Question Management Flow (Teacher)

- Teacher adds multiple-choice (MCQ) questions to an exam.
- Each question includes:
  1) Question text <br>
  2) 4 options (A, B, C, D) <br>
  3) Correct answer <br>
- Validations:
Correct answer must be A/B/C/D
Only the exam owner can add questions

## Exam Availability Flow (Student)
- Student submits exam answers
- Student submits exam answers:
  1) Exam is currently active
  2) Student role is valid
  3) Score is automatically calculated
- Score is automatically calculated
- Student has not already attempted

## Exam Attempt Flow (Student)
 - Student submits exam answers
 - System validates:
   1) Exam is currently active
   2) Student role is valid
   3) Student has not already attempted
 - Score is automatically calculated.
 - Student answers are stored separately.

## Evaluation Flow
- System compares submitted answers with correct answers
- Marks are calculated automatically
- Total score is stored in the exam attempt
- Submission timestamp is recorded

## Result Access Flow
- Student can view their own attempt history.
- Teacher can view attempts for their exams.
- Role-based restrictions are enforced for result access.



