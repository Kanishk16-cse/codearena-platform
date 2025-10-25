package com.codearena.repository;
import com.codearena.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface QuestionRepository extends JpaRepository<Question,Long>{
    List<Question> findByChallengeId(Long challengeId);
}