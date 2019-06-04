package com.htp.controller.reqest;

import java.sql.Timestamp;
import java.util.Objects;

public class UserReqest {

  private Long userId;
  private String userName;
  private String userSurname;
  private String userNumber;
  private Timestamp registerDate;
  private Integer block;

  public UserReqest(
      Long userId,
      String userName,
      String userSurname,
      String userNumber,
      Timestamp registerDate,
      Integer block) {
    this.userId = userId;
    this.userName = userName;
    this.userSurname = userSurname;
    this.userNumber = userNumber;
    this.registerDate = registerDate;
    this.block = block;
  }

  public UserReqest() {}

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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
    return "UserReqest{"
        + "userId="
        + userId
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
    UserReqest that = (UserReqest) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(userName, that.userName)
        && Objects.equals(userSurname, that.userSurname)
        && Objects.equals(userNumber, that.userNumber)
        && Objects.equals(registerDate, that.registerDate)
        && Objects.equals(block, that.block);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, userSurname, userNumber, registerDate, block);
  }
}
