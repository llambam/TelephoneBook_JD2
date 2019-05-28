package com.htp.domain.hibernate;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "adress")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "adressId")
public class HAdress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id")
    private Long adressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "floor")
    private Long floor;

    @Column(name = "apartment_number")
    private Long apartmentNumber;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private HUser user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tel_id")
    private HTelephones telephones;

    @Column(name = "adress_block")
    private Integer adressBlock;

    @Column(name = "user_adress")
    private boolean userAdress;

    public HAdress(String country, String city, String street, String houseNumber, Long floor, Long apartmentNumber, HUser user, HTelephones telephones, Integer adressBlock, boolean userAdress) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
        this.user = user;
        this.telephones = telephones;
        this.adressBlock = adressBlock;
        this.userAdress = userAdress;
    }

    public HAdress() {
    }

    public Long getAdressId() {
        return adressId;
    }

    public void setAdressId(Long adressId) {
        this.adressId = adressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public Long getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Long apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public HTelephones getTelephones() {
        return telephones;
    }

    public void setTelephones(HTelephones telephones) {
        this.telephones = telephones;
    }

    public Integer getAdressBlock() {
        return adressBlock;
    }

    public void setAdressBlock(Integer adressBlock) {
        this.adressBlock = adressBlock;
    }

    public boolean isUserAdress() {
        return userAdress;
    }

    public void setUserAdress(boolean userAdress) {
        this.userAdress = userAdress;
    }

    @Override
    public String toString() {
        return "HAdress{" +
                "adressId=" + adressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", floor=" + floor +
                ", apartmentNumber=" + apartmentNumber +
                ", user=" + user +
                ", telephones=" + telephones +
                ", adressBlock=" + adressBlock +
                ", userAdress=" + userAdress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HAdress hAdress = (HAdress) o;
        return userAdress == hAdress.userAdress &&
                Objects.equals(adressId, hAdress.adressId) &&
                Objects.equals(country, hAdress.country) &&
                Objects.equals(city, hAdress.city) &&
                Objects.equals(street, hAdress.street) &&
                Objects.equals(houseNumber, hAdress.houseNumber) &&
                Objects.equals(floor, hAdress.floor) &&
                Objects.equals(apartmentNumber, hAdress.apartmentNumber) &&
                Objects.equals(user, hAdress.user) &&
                Objects.equals(telephones, hAdress.telephones) &&
                Objects.equals(adressBlock, hAdress.adressBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adressId, country, city, street, houseNumber, floor, apartmentNumber, user, telephones, adressBlock, userAdress);
    }
}
