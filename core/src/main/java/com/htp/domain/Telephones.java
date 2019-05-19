package com.htp.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Telephones {
    private Long telId;
    private String telName;
    private String telSurname;
    private String telNumber;
    private Timestamp creationDate;
    private Integer telBlock;
    private Long userId;

    public Long getTelId() {
        return telId;
    }

    public void setTelId(Long telId) {
        this.telId = telId;
    }

    public String getTelName() {
        return telName;
    }

    public void setTelName(String telName) {
        this.telName = telName;
    }

    public String getTelSurname() {
        return telSurname;
    }

    public void setTelSurname(String telSurname) {
        this.telSurname = telSurname;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTelBlock() {
        return telBlock;
    }

    public void setTelBlock(Integer telBlock) {
        this.telBlock = telBlock;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Telephones() {
    }

    public Telephones(Long telId, String telName, String telSurname, String telNumber, Timestamp creationDate, Integer telBlock, Long userId) {
        this.telId = telId;
        this.telName = telName;
        this.telSurname = telSurname;
        this.telNumber = telNumber;
        this.creationDate = creationDate;
        this.telBlock = telBlock;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephones that = (Telephones) o;
        return Objects.equals(telId, that.telId) &&
                Objects.equals(telName, that.telName) &&
                Objects.equals(telSurname, that.telSurname) &&
                Objects.equals(telNumber, that.telNumber) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(telBlock, that.telBlock) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telId, telName, telSurname, telNumber, creationDate, telBlock, userId);
    }

    @Override
    public String toString() {
        return "Telephones{" +
                "telId=" + telId +
                ", telName='" + telName + '\'' +
                ", telSurname='" + telSurname + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", creationDate=" + creationDate +
                ", telBlock=" + telBlock +
                ", userId=" + userId +
                '}';
    }
}
