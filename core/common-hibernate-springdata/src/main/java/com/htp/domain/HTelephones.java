package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "telephones")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "telId")
public class HTelephones {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tel_id")
  private Long telId;

  @Column(name = "tel_name")
  private String telName;

  @Column(name = "tel_surname")
  private String telSurname;

  @Column(name = "tel_number")
  private String telNumber;

  @Column(name = "tel_creation_date")
  private Timestamp creationDate;

  @Column(name = "tel_block")
  private Integer telBlock;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private HUser user;

  @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "hTelephones")
    private HAdress adress;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "telephones")
    private Set<HFavorite> favorite= Collections.emptySet();

    public HTelephones(String telName, String telSurname, String telNumber, Timestamp creationDate, Integer telBlock, HUser user) {
        this.telName = telName;
        this.telSurname = telSurname;
        this.telNumber = telNumber;
        this.creationDate = creationDate;
        this.telBlock = telBlock;
        this.user = user;
    }

    public HTelephones() {
    }

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

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HTelephones that = (HTelephones) o;
        return Objects.equals(telId, that.telId) &&
                Objects.equals(telName, that.telName) &&
                Objects.equals(telSurname, that.telSurname) &&
                Objects.equals(telNumber, that.telNumber) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(telBlock, that.telBlock) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telId, telName, telSurname, telNumber, creationDate, telBlock, user);
    }

    @Override
    public String toString() {
        return "HTelephones{" +
                "telId=" + telId +
                ", telName='" + telName + '\'' +
                ", telSurname='" + telSurname + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", creationDate=" + creationDate +
                ", telBlock=" + telBlock +
                ", user=" + user +
                '}';
    }
}
