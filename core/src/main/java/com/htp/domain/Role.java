package com.htp.domain;

import java.util.Objects;

public class Role {
  private Long userRoleId;
  private Long userId;
  private String userRole;

  public Role() {}

  public Role(Long userRoleId, Long userId, String userRole) {
    this.userRoleId = userRoleId;
    this.userId = userId;
    this.userRole = userRole;
  }

  public Long getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(Long userRoleId) {
    this.userRoleId = userRoleId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  @Override
  public String toString() {
    return "Role{"
        + "userRoleId="
        + userRoleId
        + ", userId="
        + userId
        + ", userRole='"
        + userRole
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return Objects.equals(userRoleId, role.userRoleId)
        && Objects.equals(userId, role.userId)
        && Objects.equals(userRole, role.userRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userRoleId, userId, userRole);
  }
}
