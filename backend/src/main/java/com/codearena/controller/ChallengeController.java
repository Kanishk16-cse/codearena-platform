package com.codearena.controller;
import com.codearena.model.Challenge;
import com.codearena.model.Question;
import com.codearena.repository.ChallengeRepository;
import com.codearena.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/challenges")
@CrossOrigin(origins="*")
public class ChallengeController{
    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;
    public ChallengeController(ChallengeRepository c,QuestionRepository q){this.challengeRepository=c;this.questionRepository=q;}
    @GetMapping("/{id}")
    public Map<String,Object> getChallenge(@PathVariable Long id){
        Challenge ch=challengeRepository.findById(id).orElse(null);
        if(ch==null)return Map.of("error","Not found");
        List<Question> questions=questionRepository.findByChallengeId(id);
        return Map.of("challenge",ch,"questions",questions);
    }
}