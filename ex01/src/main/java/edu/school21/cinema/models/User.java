package edu.school21.cinema.models;

import java.util.List;

public class User {
  private int id;
  private String userName;
  private String password;
  private String lastName;
  private String phone;
  private List<SignIn> signInList;

  public User(int id, String userName, String password, String lastName, String phone) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.lastName = lastName;
    this.phone = phone;
  }

  public User(String userName, String password, String lastName, String phone) {
    this.userName = userName;
    this.password = password;
    this.lastName = lastName;
    this.phone = phone;
  }

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<SignIn> getSignInList() {
    return signInList;
  }

  public void setSignInList(List<SignIn> signInList) {
    this.signInList = signInList;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", userName='" + userName + '\'' +
      ", password='" + password + '\'' +
      ", lastName='" + lastName + '\'' +
      ", phone='" + phone + '\'' +
      '}';
  }
}
