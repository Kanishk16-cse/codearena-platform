package com.codearena.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long questionId;
    @Column(length=5000)
    private String code;
    private String status;
    @Column(length=2000)
    private String output;
    private Instant createdAt;
    private Integer score=0;
}