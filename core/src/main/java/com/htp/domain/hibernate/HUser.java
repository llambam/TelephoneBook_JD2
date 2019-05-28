package com.htp.domain.hibernate;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class HUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

    @Column(name = "user_number")
    private String userNumber;

    @Column(name = "register_date")
    private Timestamp registerDate;

    @Column(name = "block")
    private Integer block;


    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")  //mapped by user or roles?
    private Set<HRole> roles = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<HFavorite> favorites = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<HAdress> adresses = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<HTelephones> telephones= Collections.emptySet();



    public HUser() {}

    public HUser(Long userId, String login, String password, String userName, String userSurname, String userNumber, Timestamp registerDate, Integer block) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HUser hUser = (HUser) o;
        return Objects.equals(userId, hUser.userId) &&
                Objects.equals(login, hUser.login) &&
                Objects.equals(password, hUser.password) &&
                Objects.equals(userName, hUser.userName) &&
                Objects.equals(userSurname, hUser.userSurname) &&
                Objects.equals(userNumber, hUser.userNumber) &&
                Objects.equals(registerDate, hUser.registerDate) &&
                Objects.equals(block, hUser.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, userName, userSurname, userNumber, registerDate, block);
    }


    @Override
    public String toString() {
        return "HUser{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", registerDate=" + registerDate +
                ", block=" + block +
                '}';
    }
}
