package com.webflux.demo.model.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaseResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  private String code;
  private String message;
  private List<String> errors;
  private T data;
  private Date serverTime;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Date getServerTime() {
    return serverTime;
  }

  public void setServerTime(Date serverTime) {
    this.serverTime = serverTime;
  }
}
