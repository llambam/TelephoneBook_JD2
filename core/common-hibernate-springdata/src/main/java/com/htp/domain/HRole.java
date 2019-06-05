package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userRoleId")
public class HRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_role_id")
  private Long userRoleId;

  @JsonBackReference
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private HUser user;

  @Column(name = "user_role")
  private String userRole;

  public HRole() {}

  public HRole(HUser user, String userRole) {
    this.user = user;
    this.userRole = userRole;
  }

  public Long getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(Long userRoleId) {
    this.userRoleId = userRoleId;
  }

  public HUser getUser() {
    return user;
  }

  public void setUser(HUser user) {
    this.user = user;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HRole hRole = (HRole) o;
    return Objects.equals(userRoleId, hRole.userRoleId)
        && Objects.equals(user, hRole.user)
        && Objects.equals(userRole, hRole.userRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userRoleId, user, userRole);
  }

  @Override
  public String toString() {
    return "HRole{"
        + "userRoleId="
        + userRoleId
        + ", user="
        + user
        + ", userRole='"
        + userRole
        + '\''
        + '}';
  }
}
