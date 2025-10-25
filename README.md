# Codearena Platform
Run backend: cd backend && mvn -B -DskipTests package && java -jar target/codearena-backend-0.0.1-SNAPSHOT.jar
Build runner image: docker build -t codearena-runner:latest ./runner
Run with docker compose: docker compose up --build
Default ports: backend 8080 frontend 3000
Notes: Runner image must be built before submitting code when using dockerized judge.