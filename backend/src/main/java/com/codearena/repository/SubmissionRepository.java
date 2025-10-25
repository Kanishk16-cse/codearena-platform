package com.codearena.repository;
import com.codearena.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SubmissionRepository extends JpaRepository<Submission,Long>{
    List<Submission> findByUsername(String username);
    List<Submission> findByQuestionId(Long questionId);
}