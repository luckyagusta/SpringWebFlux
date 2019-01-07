package com.webflux.demo.controller;

import com.webflux.demo.library.BusinessException;
import com.webflux.demo.model.response.BaseResponse;
import com.webflux.demo.model.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessLogicException(BusinessException ble) {
      LOGGER.info("BusinessLogicException = {}", ble);
      return CommonResponse.constructResponse(ble.getCode(), ble.getMessage(), null, null);
    }
  }
