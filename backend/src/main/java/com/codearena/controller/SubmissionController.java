package com.codearena.controller;
import com.codearena.dto.*;
import com.codearena.model.Submission;
import com.codearena.repository.SubmissionRepository;
import com.codearena.service.JudgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/submissions")
@CrossOrigin(origins="*")
public class SubmissionController{
    private final SubmissionRepository submissionRepository;
    private final JudgeService judgeService;
    public SubmissionController(SubmissionRepository r,JudgeService j){this.submissionRepository=r;this.judgeService=j;}
    @PostMapping
    public ResponseEntity<SubmitResponse> submit(@RequestBody SubmitRequest req){
        Submission s=new Submission();
        s.setUsername(req.getUsername());
        s.setQuestionId(req.getQuestionId());
        s.setCode(req.getCode());
        s.setStatus("Pending");
        s.setOutput("");
        Submission saved=judgeService.enqueueAndJudge(s);
        return ResponseEntity.ok(new SubmitResponse(saved.getId()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmission(@PathVariable Long id){
        return submissionRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}