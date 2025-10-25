# About Project

## This system is structured around three major components:

Frontend – a modern, responsive web interface built with React and Tailwind CSS.
Backend – a Spring Boot–based service providing APIs for authentication, problem management, and submission tracking.
Runner Service – a secure code execution environment powered by Docker for sandboxed program evaluation.

# Architechture
+--------------------------+
|        Frontend          |
| (React, Tailwind, Vite)  |
|--------------------------|
| UI, Routing, Code Editor |
+-----------+--------------+
            |
            v
+-----------+--------------+
|           Backend         |
|   (Spring Boot, REST API) |
|----------------------------|
| Auth | Submissions | Problems |
+-----------+--------------+
            |
            v
+-----------+--------------+
|        Runner Service     |
|   (Docker Sandbox Env)    |
| Executes & Validates Code |
+----------------------------+

# Features 
Features

User Authentication – Secure login and registration with JWT-based authentication.
Problem Management – Create, list, and update coding problems.
Code Execution – Safely execute submitted code inside Docker containers.
Submission Evaluation – Automatic result checking against predefined test cases.
Modular Design – Independent frontend, backend, and runner services for scalability.

## How to run this project 
Run backend: cd backend && mvn -B -DskipTests package && java -jar target/codearena-backend-0.0.1-SNAPSHOT.jar
Build runner image: docker build -t codearena-runner:latest ./runner
Run with docker compose: docker compose up --build
Default ports: backend 8080 frontend 3000
Notes: Runner image must be built before submitting code when using dockerized judge.
