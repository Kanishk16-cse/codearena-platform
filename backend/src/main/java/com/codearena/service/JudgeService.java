package com.codearena.service;
import com.codearena.model.Question;
import com.codearena.model.Submission;
import com.codearena.repository.QuestionRepository;
import com.codearena.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.time.Instant;
import java.util.concurrent.*;
@Service
public class JudgeService{
    private final SubmissionRepository submissionRepository;
    private final QuestionRepository questionRepository;
    private final ExecutorService executor=Executors.newFixedThreadPool(4);
    public JudgeService(SubmissionRepository s,QuestionRepository q){this.submissionRepository=s;this.questionRepository=q;}
    public Submission enqueueAndJudge(Submission submission){
        submission.setStatus("Pending");
        submission.setCreatedAt(Instant.now());
        Submission saved=submissionRepository.save(submission);
        executor.submit(()->runDockerJudge(saved.getId()));
        return saved;
    }
    private void runDockerJudge(Long id){
        try{
            Submission sub=submissionRepository.findById(id).orElseThrow();
            sub.setStatus("Running");
            submissionRepository.save(sub);
            Question q=questionRepository.findById(sub.getQuestionId()).orElse(null);
            if(q==null){sub.setStatus("Error");sub.setOutput("Question not found");submissionRepository.save(sub);return;}
            Path tmpDir=Files.createTempDirectory("codearena_"+id+"_");
            Path srcFile=tmpDir.resolve("solution.py");
            Files.writeString(srcFile, sub.getCode()==null?"":sub.getCode());
            ProcessBuilder pb=new ProcessBuilder("docker","run","--rm","-v",tmpDir.toString()+":/workspace","codearena-runner:latest","python","/workspace/solution.py");
            pb.redirectErrorStream(true);
            Process p=pb.start();
            StringBuilder out=new StringBuilder();
            try(InputStream is=p.getInputStream();BufferedReader br=new BufferedReader(new InputStreamReader(is))){
                String line;
                while((line=br.readLine())!=null)out.append(line).append("\n");
            }
            boolean finished=p.waitFor(8,TimeUnit.SECONDS);
            if(!finished){p.destroyForcibly();sub.setStatus("Error");sub.setOutput("Timeout");submissionRepository.save(sub);Files.walk(tmpDir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);return;}
            String output=out.toString().trim();
            String expected=q.getExpectedOutput()==null?"":q.getExpectedOutput().trim();
            if(output.equals(expected)){sub.setStatus("Accepted");sub.setOutput(output);sub.setScore(1);}else{sub.setStatus("Wrong Answer");sub.setOutput("Expected: "+expected+" Got: "+output);sub.setScore(0);}
            submissionRepository.save(sub);
            Files.walk(tmpDir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }catch(Exception e){
            submissionRepository.findById(id).ifPresent(s->{s.setStatus("Error");s.setOutput(e.getMessage());submissionRepository.save(s);});
        }
    }
}