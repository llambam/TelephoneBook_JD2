package com.htp.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Role {
    private Long userRoleId;
    private Long userId;
    private String userRole;
    private Timestamp lastChangeDate;

    public Role() {
    }

    public Role(Long userRoleId, Long userId, String userRole, Timestamp lastChangeDate) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.userRole = userRole;
        this.lastChangeDate = lastChangeDate;
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

    public Timestamp getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Timestamp lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(userRoleId, role.userRoleId) &&
                Objects.equals(userId, role.userId) &&
                Objects.equals(userRole, role.userRole) &&
                Objects.equals(lastChangeDate, role.lastChangeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId, userId, userRole, lastChangeDate);
    }

    @Override
    public String toString() {
        return "Role{" +
                "userRoleId=" + userRoleId +
                ", userId=" + userId +
                ", userRole='" + userRole + '\'' +
                ", lastChangeDate=" + lastChangeDate +
                '}';
    }
}
