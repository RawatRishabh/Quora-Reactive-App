package com.example.demo.adapter;

import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Questions;

public class QuestionAdapter
{
    public static QuestionResponseDTO toQuestionResponseDTO(Questions questions)
    {
        return QuestionResponseDTO.builder()
                .id(questions.getId())
                .title(questions.getTitle())
                .content(questions.getContent())
                .createdAt(questions.getCreatedAt())
                .updatedAt(questions.getUpdatedAt())
                .build();
    }
}
