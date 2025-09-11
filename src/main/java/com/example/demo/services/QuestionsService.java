package com.example.demo.services;

import com.example.demo.adapter.QuestionAdapter;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Questions;
import com.example.demo.repositories.IQuestionsRepository;
import com.example.demo.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionsService implements  IQuestionsService
{

    private final IQuestionsRepository questionsRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO requestDTO) {
        Questions questions = Questions.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionsRepository.save(questions).map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error creating Question "+error))
                .doOnSuccess(response -> System.out.println("Question created successfully "+response));
    }

    @Override
    // flux itself stream of list
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable = PageRequest.of(0,size);
        if(CursorUtils.checkCursor(cursor))
        {
            return questionsRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)  // take only 'size' number of records from previous records results
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error -> System.out.println("Hello" + error))
                    .doOnComplete(()-> System.out.println("Question found"));
        }
        LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
        return questionsRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp,pageable)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Hello" + error))
                .doOnComplete(()-> System.out.println("Question found"));
    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
        return questionsRepository.deleteById(id)
                .doOnError(error -> System.out.println("Question not deleted "+error));
    }

    @Override
    public Flux<QuestionResponseDTO> findByTitleOrContentContainingIgnoreCase(String searchTerm, int page, int size) {
        return questionsRepository.findByTitleOrContentContainingIgnoreCase(searchTerm,PageRequest.of(page,size))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error-> System.out.println("Questions Not found "+error))
                .doOnComplete(()-> System.out.println("Question found"));
    }

}
// Pagination parameter homework