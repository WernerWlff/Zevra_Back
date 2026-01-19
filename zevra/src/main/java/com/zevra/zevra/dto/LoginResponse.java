package com.zevra.zevra.dto;

import java.util.UUID;

public class LoginResponse {

  private String token;
  private UUID id;
  private String username;
  private String email;
  private String firstname;
  private String lastname;
  private String message;

  public LoginResponse() {}

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "LoginResponse{"
        + "token='"
        + (token != null ? "***" : null)
        + '\''
        + ", id="
        + id
        + ", username='"
        + username
        + '\''
        + ", email='"
        + email
        + '\''
        + ", firstname='"
        + firstname
        + '\''
        + ", lastname='"
        + lastname
        + '\''
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
