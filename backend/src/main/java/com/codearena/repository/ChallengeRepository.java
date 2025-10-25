package com.codearena.repository;
import com.codearena.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ChallengeRepository extends JpaRepository<Challenge,Long>{}