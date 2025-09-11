package com.example.demo.services;

import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Questions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionsService
{
    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO requestDTO);
    Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);
    Mono<Void> deleteQuestionById(String id);
    Flux<QuestionResponseDTO> findByTitleOrContentContainingIgnoreCase(String searchTerm, int page, int size);
}
