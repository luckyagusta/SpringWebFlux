package com.webflux.demo.service.impl;

import com.webflux.demo.entity.EmailPriority;
import com.webflux.demo.entity.constant.DemoConstant;
import com.webflux.demo.library.BusinessException;
import com.webflux.demo.repository.EmailPriorityRepository;
import com.webflux.demo.service.EmailPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmailPriorityServiceImpl implements EmailPriorityService {

  @Autowired
  private EmailPriorityRepository emailPriorityRepository;

  @Override
  public Mono<EmailPriority> findByPriorityName(String name) {
    return emailPriorityRepository.findByPriorityName(name);
  }

  @Override
  public Mono<EmailPriority> save(EmailPriority emailPriority) {
    return Mono.defer(() ->
      emailPriorityRepository.findByPriorityName(emailPriority.getPriorityName())
          .map(priorityExist -> {
            if(priorityExist.getPriorityName() != null) {
              throw new BusinessException(
                  DemoConstant.PRIORITY_NAME_EXIST.getCode(),
                  DemoConstant.PRIORITY_NAME_EXIST.getMessage());
            }
            return priorityExist;
          })
          .switchIfEmpty(emailPriorityRepository.insert(emailPriority))
    );
  }

  @Override
  public Mono<EmailPriority> update(EmailPriority emailPriority, String priorityName) {
    return emailPriorityRepository.findByPriorityName(priorityName)
        .switchIfEmpty(Mono.error(new BusinessException(
            DemoConstant.DATA_NOT_EXIST.getCode(),
            DemoConstant.DATA_NOT_EXIST.getMessage())))
        .flatMap(existPriority->
            checkExistEmailPriorityName(existPriority, emailPriority.getPriorityName())
        )
        .map(existPriority->{
          existPriority.setSmtpUsername(emailPriority.getSmtpUsername());
          existPriority.setSmtpPassword(emailPriority.getSmtpPassword());
          existPriority.setPriorityOrder(emailPriority.getPriorityOrder());
          existPriority.setPriorityName(emailPriority.getPriorityName());
          existPriority.setSmtpPort(emailPriority.getSmtpPort());
          existPriority.setSmtpHost(emailPriority.getSmtpHost());
          return existPriority;
        });
  }

  private Mono<EmailPriority> checkExistEmailPriorityName(EmailPriority existingPriority,
      String newPriorityName) {
    return emailPriorityRepository
        .findByPriorityNameAndIdIsNot(newPriorityName, existingPriority.getId()
        ).flatMap(emailPriority ->
            Mono.error(new BusinessException(DemoConstant.DUPLICATE_DATA.getCode(),
                DemoConstant.DUPLICATE_DATA.getMessage()))
        ).switchIfEmpty(
            Mono.defer(() ->
                Mono.just(existingPriority)
            )
        ).map(o ->
            (EmailPriority) o
        );
  }
}
