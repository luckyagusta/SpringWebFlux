package com.webflux.demo.service;

import com.webflux.demo.entity.EmailPriority;
import reactor.core.publisher.Mono;

public interface EmailPriorityService {
  public Mono<EmailPriority> findByPriorityName(String name);

  public Mono<EmailPriority> save(EmailPriority emailPriority);

  public Mono<EmailPriority> update(EmailPriority emailPriority, String priorityName);
}
