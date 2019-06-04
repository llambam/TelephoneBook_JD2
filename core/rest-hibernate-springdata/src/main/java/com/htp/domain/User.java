package com.htp.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class User {

  private Long userId;
  private String login;
  private String password;
  private String userName;
  private String userSurname;
  private String userNumber;
  private Timestamp registerDate;
  private Integer block;

  public User() {}

  public User(
      Long userId,
      String login,
      String password,
      String userName,
      String userSurname,
      String userNumber,
      Timestamp registerDate,
      Integer block) {
    this.userId = userId;
    this.login = login;
    this.password = password;
    this.userName = userName;
    this.userSurname = userSurname;
    this.userNumber = userNumber;
    this.registerDate = registerDate;
    this.block = block;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserSurname() {
    return userSurname;
  }

  public void setUserSurname(String userSurname) {
    this.userSurname = userSurname;
  }

  public String getUserNumber() {
    return userNumber;
  }

  public void setUserNumber(String userNumber) {
    this.userNumber = userNumber;
  }

  public Timestamp getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Timestamp registerDate) {
    this.registerDate = registerDate;
  }

  public Integer getBlock() {
    return block;
  }

  public void setBlock(Integer block) {
    this.block = block;
  }

  @Override
  public String toString() {
    return "User{"
        + "userId="
        + userId
        + ", login='"
        + login
        + '\''
        + ", password='"
        + password
        + '\''
        + ", userName='"
        + userName
        + '\''
        + ", userSurname='"
        + userSurname
        + '\''
        + ", userNumber='"
        + userNumber
        + '\''
        + ", registerDate="
        + registerDate
        + ", block="
        + block
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(userId, user.userId)
        && Objects.equals(login, user.login)
        && Objects.equals(password, user.password)
        && Objects.equals(userName, user.userName)
        && Objects.equals(userSurname, user.userSurname)
        && Objects.equals(userNumber, user.userNumber)
        && Objects.equals(registerDate, user.registerDate)
        && Objects.equals(block, user.block);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        userId, login, password, userName, userSurname, userNumber, registerDate, block);
  }
}
