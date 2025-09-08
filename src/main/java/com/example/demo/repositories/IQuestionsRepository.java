package com.example.demo.repositories;

import com.example.demo.models.Questions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IQuestionsRepository extends ReactiveMongoRepository<Questions,String>
{
    @Query("{'$or':[{'title':{$regex:?0,$options:'i'}},{'content':{$regex:?0,$options:'i'}}]}")
    Flux<Questions> findByTitleOrContentContainingIgnoreCase(String searchTerm, PageRequest pageable);
}
