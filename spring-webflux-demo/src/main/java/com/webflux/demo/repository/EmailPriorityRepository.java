package com.webflux.demo.repository;

import com.webflux.demo.entity.EmailPriority;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmailPriorityRepository extends ReactiveMongoRepository<EmailPriority, String> {
  public Mono<EmailPriority> findByPriorityName(String priorityName);

  Mono<EmailPriority> findByPriorityNameAndIdIsNot(String priorityName, String id);
}
