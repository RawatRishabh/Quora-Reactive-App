package com.example.demo.repositories;

import com.example.demo.models.Questions;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IQuestionsRepository extends ReactiveMongoRepository<Questions,String>
{

}
