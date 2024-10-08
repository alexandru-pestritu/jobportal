# 💼 Job Portal Web Application

This repository contains the source code for a **Job Portal** web application developed using **Java** and **Spring Boot**. The application allows recruiters to post job openings and job candidates to search and apply for jobs. It includes features such as user authentication, job search, and profile management for both recruiters and job seekers.

## ✨ Features

### 👩‍💼 Recruiter Features
- **Post New Jobs**: Recruiters can create new job posts.
- **View Jobs**: Recruiters can see a list of their posted jobs.
- **View Applicants**: Recruiters can view a list of candidates who applied for a specific job.
- **Profile Management**: Recruiters can edit their profile and upload a profile photo.

### 👨‍💻 Candidate Features
- **Search Jobs**: Job seekers can search for jobs based on keywords and filters.
- **Apply for Jobs**: Candidates can apply for jobs and upload their resumé/CV.
- **View Applied Jobs**: Candidates can see a list of jobs they have applied for.
- **Profile Management**: Candidates can edit their profile, upload a profile photo, and add skills.

### 🔒 Common Features
- **User Registration**: Both recruiters and job seekers can register for a new account.
- **Login/Logout**: Secure login and logout functionality.

## 💻 Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Thymeleaf** for server-side rendering
- **JPA/Hibernate** for ORM and database management
- **MySQL** for the database
- **Maven** for dependency management
- **Bootstrap**, **jQuery**, and **Font Awesome** for front-end styling and components

## 🛠 Setup Instructions

### Prerequisites
- JDK 21 or higher
- MySQL Server
- MySQL Workbench (optional, but helpful for database management)
- Maven

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/alexandru-pestritu/jobportal.git
   cd job-portal
   ```

2. Configure application properties: In src/main/resources/application.properties, configure the database connection:
   ```bash
   spring.datasource.url=jdbc:mysql://your_mysql_url:3306/jobportal
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Access the application at ```http://localhost:8080```.

## 🏗 Application Architecture
The Job Portal follows a layered architecture:

- **Controller Layer**: Manages incoming HTTP requests and calls the appropriate services.
- **Service Layer**: Contains the business logic of the application.
- **Repository Layer**: Handles data persistence with JPA/Hibernate.
- **View Layer (Thymeleaf Templates)**: Renders the UI for the users.

## 🗃 Database Structure
The application uses MySQL to manage users, jobs, and applications. Below is an overview of the key entities:
- **Users**: Stores basic information about a user (email, password, role).
- **UsersType**: Defines the role of the user (recruiter or job seeker).
- **JobPostActivity**: Stores job post details (title, description, salary, location).
- **JobSeekerProfile**: Stores information about job seekers (name, city, skills).
- **RecruiterProfile**: Stores information about recruiters (name, company).
- **Skills**: Stores skills associated with job seekers.
- **JobSeekerApply**: Tracks job applications.
- **JobSeekerSave**: Tracks saved jobs for candidates.

### Database Diagram
![EER_diagram](https://github.com/user-attachments/assets/dd6cabc4-b18b-421a-8627-cd85bc5c0d58)

