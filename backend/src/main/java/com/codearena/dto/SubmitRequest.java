package com.codearena.dto;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubmitRequest{
    private String username;
    private Long questionId;
    private String code;
    private String language;
}