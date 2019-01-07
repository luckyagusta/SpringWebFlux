package com.webflux.demo.controller.constant;

public enum ResponseCode {
  SUCCESS("SUCCESS", "SUCCESS")  ,
  SYSTEM_ERROR("SYSTEM_ERROR", "Contact our team"),
  DUPLICATE_DATA("DUPLICATE_DATA", "Duplicate data"),
  DATA_NOT_EXIST("DATA_NOT_EXIST", "No data exist"),
  RUNTIME_ERROR("RUNTIME_ERROR", "Runtime Error"),
  BIND_ERROR("BIND_ERROR", "Please fill in mandatory parameter"),
  MIME_ERROR("MIME_ERROR", "Mime Error"),
  METHOD_ARGUMENTS_NOT_VALID("METHOD_ARGUMENTS_NOT_VALID", "Method arguments not valid"),
  DATA_NOT_VALID("DATA_NOT_VALID", "Data not valid"),
  PRIORITY_NAME_EXIST("PRIORITY_NAME_EXIST", "PRIORITY_NAME_EXIST");

  private String code;
  private String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
