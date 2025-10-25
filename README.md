# Codearena Platform

## About the project 
CodeArena Platform is a web-based coding competition and practice system that allows users to write, run, and submit code in a secure sandboxed environment. It provides a modern interface for solving programming problems and supports real-time evaluation through containerized code execution.

## Features
User-friendly frontend built with React and Tailwind CSS.
Backend developed using Java (Spring Boot) for robust API management.
Docker-based runner service for isolated and secure code execution.
Supports container orchestration via Docker Compose for seamless deployment.

## Tech Stack

Frontend: React, Vite, Tailwind CSS
Backend: Java (Spring Boot), Maven
Runner: Docker

## How to run this project 
Run backend: cd backend && mvn -B -DskipTests package && java -jar target/codearena-backend-0.0.1-SNAPSHOT.jar
Build runner image: docker build -t codearena-runner:latest ./runner
Run with docker compose: docker compose up --build
Default ports: backend 8080 frontend 3000
Notes: Runner image must be built before submitting code when using dockerized judge.
