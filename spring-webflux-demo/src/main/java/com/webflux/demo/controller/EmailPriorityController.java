package com.webflux.demo.controller;

import com.webflux.demo.controller.constant.ApiPath;
import com.webflux.demo.controller.constant.ResponseCode;
import com.webflux.demo.entity.EmailPriority;
import com.webflux.demo.model.request.EmailPriorityRequest;
import com.webflux.demo.model.response.BaseResponse;
import com.webflux.demo.model.response.CommonResponse;
import com.webflux.demo.model.response.EmailPriorityResponse;
import com.webflux.demo.service.EmailPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EmailPriorityController {

  @Autowired
  private EmailPriorityService emailPriorityService;
  private BeanMapper mapper = Mapper.BEAN_MAPPER;

  @GetMapping(path = ApiPath.EMAIL_PRIORITY_DETAIL)
  public Mono<BaseResponse<EmailPriorityResponse>> emailPriorityDetail(
      @PathVariable(value = "priorityName") String priorityName
  ){
    return emailPriorityService.findByPriorityName(priorityName)
        .map(emailPriority -> {
      EmailPriorityResponse response = mapper.map(emailPriority);
      return CommonResponse.constructResponse(
          ResponseCode.SUCCESS.getCode(),
          ResponseCode.SUCCESS.getMessage(),
          null,
          response
      );
    });
  }

  @PostMapping(path = ApiPath.EMAIL_PRIORITY)
  public Mono<BaseResponse<String>> saveEmailPriority(
      @RequestBody EmailPriorityRequest emailPriorityRequest){
    EmailPriority emailPriority = mapper.map(emailPriorityRequest);
    return emailPriorityService.save(emailPriority)
        .map(result ->
          CommonResponse.constructResponse(
              ResponseCode.SUCCESS.getCode(),
              ResponseCode.SUCCESS.getMessage(),
              null,
              "Data has successfully saved."
          )
        );
  }

  @PutMapping(path = ApiPath.EMAIL_PRIORITY)
  public Mono<BaseResponse<String>> updateEmailPriority(
      @RequestBody EmailPriorityRequest emailPriorityRequest,
      @RequestParam String priorityName){
    EmailPriority emailPriority = mapper.map(emailPriorityRequest);
    return emailPriorityService.update(emailPriority, priorityName)
        .map(result ->
            CommonResponse.constructResponse(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                null,
                "Data has successfully saved."
            )
        );
  }


}
