package edu.school21.cinema.models;

import java.time.LocalDateTime;

public class SignIn {

  private String ip;

  private LocalDateTime date;

  public SignIn(String ip, LocalDateTime date) {
    this.ip = ip;
    this.date = date;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "SignIn{" +
      "ip='" + ip + '\'' +
      ", date=" + date +
      '}';
  }
}
