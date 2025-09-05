package com.example.demo.services;

import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Questions;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionsService
{
    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO requestDTO);
    Flux<QuestionResponseDTO> getAllQuestions();
    Mono<Void> deleteQuestionById(String id);
}
