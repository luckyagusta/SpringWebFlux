package com.webflux.demo.controller;

import com.webflux.demo.entity.EmailPriority;
import com.webflux.demo.model.request.EmailPriorityRequest;
import com.webflux.demo.model.response.EmailPriorityResponse;
import org.mapstruct.Mapper;

@Mapper
public interface BeanMapper {
  EmailPriority map(EmailPriorityRequest emailPriorityRequest);

  EmailPriorityResponse map(EmailPriority emailPriorityRequest);
}
