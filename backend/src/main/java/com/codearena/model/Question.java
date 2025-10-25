package com.codearena.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long challengeId;
    private String title;
    @Column(length=2000)
    private String inputData;
    @Column(length=2000)
    private String expectedOutput;
}