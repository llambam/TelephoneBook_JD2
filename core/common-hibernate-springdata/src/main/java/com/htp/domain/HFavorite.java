package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favorite")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "favoriteId")
public class HFavorite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "favorite_id")
  private Long favoriteId;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private HUser user;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "tel_id")
  private HTelephones telephones;

  @Column(name = "favorite_block")
  private Integer favoriteBlock;

  public HFavorite() {}

  public HFavorite(HUser user, HTelephones telephones, Integer favoriteBlock) {
    this.user = user;
    this.telephones = telephones;
    this.favoriteBlock = favoriteBlock;
  }

  public Long getFavoriteId() {
    return favoriteId;
  }

  public void setFavoriteId(Long favoriteId) {
    this.favoriteId = favoriteId;
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

  public Integer getFavoriteBlock() {
    return favoriteBlock;
  }

  public void setFavoriteBlock(Integer favoriteBlock) {
    this.favoriteBlock = favoriteBlock;
  }

  @Override
  public String toString() {
    return "HFavorite{"
        + "favoriteId="
        + favoriteId
        + ", user="
        + user
        + ", telephones="
        + telephones
        + ", favoriteBlock="
        + favoriteBlock
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HFavorite hFavorite = (HFavorite) o;
    return Objects.equals(favoriteId, hFavorite.favoriteId)
        && Objects.equals(user, hFavorite.user)
        && Objects.equals(telephones, hFavorite.telephones)
        && Objects.equals(favoriteBlock, hFavorite.favoriteBlock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(favoriteId, user, telephones, favoriteBlock);
  }
}
