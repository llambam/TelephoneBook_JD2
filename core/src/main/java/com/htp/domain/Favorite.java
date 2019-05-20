package com.htp.domain;

import java.util.Objects;

public class Favorite {
  private Long favoriteId;
  private Long userId;
  private Long telId;
  private Integer favoriteBlock;

  public Favorite(Long favoriteId, Long userId, Long telId, Integer favoriteBlock) {
    this.favoriteId = favoriteId;
    this.userId = userId;
    this.telId = telId;
    this.favoriteBlock = favoriteBlock;
  }

  public Favorite() {}

  public Long getFavoriteId() {
    return favoriteId;
  }

  public void setFavoriteId(Long favoriteId) {
    this.favoriteId = favoriteId;
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

  public Integer getFavoriteBlock() {
    return favoriteBlock;
  }

  public void setFavoriteBlock(Integer favoriteBlock) {
    this.favoriteBlock = favoriteBlock;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Favorite favorite = (Favorite) o;
    return Objects.equals(favoriteId, favorite.favoriteId)
        && Objects.equals(userId, favorite.userId)
        && Objects.equals(telId, favorite.telId)
        && Objects.equals(favoriteBlock, favorite.favoriteBlock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(favoriteId, userId, telId, favoriteBlock);
  }

  @Override
  public String toString() {
    return "Favorite{"
        + "favoriteId="
        + favoriteId
        + ", userId="
        + userId
        + ", telId="
        + telId
        + ", favoriteBlock="
        + favoriteBlock
        + '}';
  }
}
