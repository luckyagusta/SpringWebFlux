package com.webflux.demo.controller.constant;

public class ApiPath {
  public static final String BASE_PATH = "/webFlux";
  public static final String EMAIL_PRIORITY = BASE_PATH + "/emailPriority";
  public static final String EMAIL_PRIORITY_DETAIL = EMAIL_PRIORITY + "/{priorityName}";
}
