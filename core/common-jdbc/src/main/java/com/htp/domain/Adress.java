package com.htp.domain;

import java.util.Objects;

public class Adress {
  private Long adressId;
  private String country;
  private String city;
  private String street;
  private String houseNumber;
  private Long floor;
  private Long apartmentNumber;
  private Long userId;
  private Long telId;
  private Integer adressBlock;
  private boolean userAdress;

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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getTelId() {
    return telId;
  }

  public void setTelId(Long telId) {
    this.telId = telId;
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

  public Adress(
      Long adressId,
      String country,
      String city,
      String street,
      String houseNumber,
      Long floor,
      Long apartmentNumber,
      Long userId,
      Long telId,
      Integer adressBlock,
      boolean userAdress) {
    this.adressId = adressId;
    this.country = country;
    this.city = city;
    this.street = street;
    this.houseNumber = houseNumber;
    this.floor = floor;
    this.apartmentNumber = apartmentNumber;
    this.userId = userId;
    this.telId = telId;
    this.adressBlock = adressBlock;
    this.userAdress = userAdress;
  }

  public Adress() {}

  @Override
  public String toString() {
    return "Adress{"
        + "adressId="
        + adressId
        + ", country='"
        + country
        + '\''
        + ", city='"
        + city
        + '\''
        + ", street='"
        + street
        + '\''
        + ", houseNumber='"
        + houseNumber
        + '\''
        + ", floor="
        + floor
        + ", apartmentNumber="
        + apartmentNumber
        + ", userId="
        + userId
        + ", telId="
        + telId
        + ", adressBlock="
        + adressBlock
        + ", userAdress="
        + userAdress
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Adress adress = (Adress) o;
    return userAdress == adress.userAdress
        && Objects.equals(adressId, adress.adressId)
        && Objects.equals(country, adress.country)
        && Objects.equals(city, adress.city)
        && Objects.equals(street, adress.street)
        && Objects.equals(houseNumber, adress.houseNumber)
        && Objects.equals(floor, adress.floor)
        && Objects.equals(apartmentNumber, adress.apartmentNumber)
        && Objects.equals(userId, adress.userId)
        && Objects.equals(telId, adress.telId)
        && Objects.equals(adressBlock, adress.adressBlock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        adressId,
        country,
        city,
        street,
        houseNumber,
        floor,
        apartmentNumber,
        userId,
        telId,
        adressBlock,
        userAdress);
  }
}
