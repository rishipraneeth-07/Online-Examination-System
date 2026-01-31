# Online Examination System 

### It is a backend system that allows teachers to create and conduct timed exams, students to attempt them securely, and the system to evaluate and publish results.


##  Application Flow – Online Examination System
### 1. User & Authentication Flow

- The system supports three roles: ADMIN, TEACHER, and STUDENT.

- Users authenticate using their credentials.

- On successful login, an authentication token is issued.

- All subsequent requests are authorized based on the user’s role.

### 2. User Management Flow (Admin)

- Admin creates user accounts.

- Admin assigns roles (TEACHER or STUDENT).

- Only active users are allowed to access the system.

- Admin has access to all users and results.

### 3. Exam Creation Flow (Teacher)

- Teacher logs in and creates an exam.

- Exam includes:

- Title

- Duration

- Start time and end time

- Exam status transitions from Scheduled → Active → Closed.

- Only the exam creator can manage that exam.

### 4. Question Management Flow (Teacher)

- Teacher adds questions to an exam.

- Questions are multiple-choice (MCQ).

- Each question has predefined marks.

- Questions cannot be modified once the exam becomes active.

### 5. Exam Availability Flow (Student)

- Student logs in and views available exams.

- Only exams that are:

- Within the scheduled time window

-Not already attempted are visible to the student.

### 6. Exam Attempt Flow (Student)

- Student starts the exam.

- System validates:

- Exam is active

- Student has not attempted before

- Exam attempt is created and timer starts.

- Student submits answers.

- Exam is automatically submitted when time expires.

### 7. Evaluation Flow

- The system automatically evaluates MCQ answers.

- Marks are calculated based on correct responses.

- Total score and percentage are computed.

### 8. Result Generation Flow

- Result is generated after evaluation.

- Result includes:

- Total score

- Percentage

- Pass/Fail status

- Results are finalized once generated.

### 9. Result Access Flow

- Student: Can view only their own results.

- Teacher: Can view results of exams they created.

- Admin: Can view all exam results.

### 10. Authorization Flow

- Every request is validated for authentication.

- Role-based authorization is enforced across all APIs.

- Unauthorized access is restricted globally.

### Future enhancements
