package com.webflux.demo.model.response;

public class EmailPriorityResponse {
  private String priorityName;
  private Integer priorityOrder;
  private String smtpHost;
  private String smtpPort;
  private String smtpUsername;
  private String smtpPassword;

  public String getPriorityName() {
    return priorityName;
  }

  public void setPriorityName(String priorityName) {
    this.priorityName = priorityName;
  }

  public Integer getPriorityOrder() {
    return priorityOrder;
  }

  public void setPriorityOrder(Integer priorityOrder) {
    this.priorityOrder = priorityOrder;
  }

  public String getSmtpHost() {
    return smtpHost;
  }

  public void setSmtpHost(String smtpHost) {
    this.smtpHost = smtpHost;
  }

  public String getSmtpPort() {
    return smtpPort;
  }

  public void setSmtpPort(String smtpPort) {
    this.smtpPort = smtpPort;
  }

  public String getSmtpUsername() {
    return smtpUsername;
  }

  public void setSmtpUsername(String smtpUsername) {
    this.smtpUsername = smtpUsername;
  }

  public String getSmtpPassword() {
    return smtpPassword;
  }

  public void setSmtpPassword(String smtpPassword) {
    this.smtpPassword = smtpPassword;
  }
}
