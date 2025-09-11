package com.example.demo.controllers;

import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Questions;
import com.example.demo.services.IQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionsController
{
    private final IQuestionsService iQuestionsService;
    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO requestDTO)
    {
        return iQuestionsService.createQuestion(requestDTO)
                .doOnError(error -> System.out.println("Error creating Question "+error)) // side effect operator
                .doOnSuccess(response -> System.out.println("Question created successfully "+response)); // side effect operator
    }

    @GetMapping()
        public Flux<QuestionResponseDTO> getAllQuestions(   @RequestParam(required = false) String cursor,
                                                            @RequestParam(defaultValue = "10") int size)
        {
            return iQuestionsService.getAllQuestions(cursor,size)
                    .doOnError(error -> System.out.println("Collection is empty"+ error));
        }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id)
    {
        return iQuestionsService.deleteQuestionById(id);
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> findByTitleOrContentContainingIgnoreCase(@RequestParam String searchTerm, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        return iQuestionsService.findByTitleOrContentContainingIgnoreCase(searchTerm,page,size);
    }

    @GetMapping("/tag/{tag}")
    public Flux<QuestionResponseDTO> getQuestionsByTag(@PathVariable String tag, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int page)
    {
        return null;
    }

}
