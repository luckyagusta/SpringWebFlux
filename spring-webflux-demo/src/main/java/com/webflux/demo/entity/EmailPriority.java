package com.webflux.demo.entity;

import com.webflux.demo.entity.constant.CollectionName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = CollectionName.EMAIL_PRIORITY)
public class EmailPriority {
  @Id
  private String id;
  private String priorityName;
  private Integer priorityOrder;
  private String smtpHost;
  private String smtpPort;
  private String smtpUsername;
  private String smtpPassword;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
