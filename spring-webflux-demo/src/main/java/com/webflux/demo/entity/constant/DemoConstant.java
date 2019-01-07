package com.webflux.demo.entity.constant;

public enum DemoConstant {

  DATA_NOT_EXIST("DATA_NOT_EXIST", "No data exist"),
  DUPLICATE_DATA("DUPLICATE_DATA", "Duplicate data"),
  PRIORITY_NAME_EXIST("PRIORITY_NAME_EXIST", "PRIORITY_NAME_EXIST");

  private String code;
  private String message;

  DemoConstant(String code, String message) {
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
