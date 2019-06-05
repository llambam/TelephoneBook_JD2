package com.htp.repository.impl;

import com.htp.domain.Favorite;
import com.htp.repository.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class FavoriteDaoImpl implements FavoriteDao {

  private static final String FAVORITE_ID = "favorite_id";
  private static final String USER_ID = "user_id";
  private static final String TEL_ID = "tel_id";
  private static final String FAVORITE_BLOCK = "favorite_block";

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private Favorite getFavRowMapper(ResultSet resultSet, int i) throws SQLException {
    Favorite favorite = new Favorite();
    favorite.setFavoriteId(resultSet.getLong(FAVORITE_ID));
    favorite.setUserId(resultSet.getLong(USER_ID));
    favorite.setTelId(resultSet.getLong(TEL_ID));
    favorite.setFavoriteBlock(resultSet.getInt(FAVORITE_BLOCK));
    return favorite;
  }

  private static final String SELECT_ALL = "select * from favorite";

  @Override
  public List<Favorite> findAll() {
    return namedParameterJdbcTemplate.query(SELECT_ALL, this::getFavRowMapper);
  }

  private static final String SELECT_ALL_BY_FAVORITE_ID =
      "select * from favorite where favorite_id = :favoriteId";

  @Override
  public Favorite findById(Long id) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("favoriteId", id);

    return namedParameterJdbcTemplate.queryForObject(
        SELECT_ALL_BY_FAVORITE_ID, params, this::getFavRowMapper);
  }

  @Override
  public void delete(Long id) {}

  private static final String INSERT_FAVORITE =
      "INSERT INTO favorite (user_id, tel_id, favorite_block) "
          + "VALUES (:userId, :telId, favoriteBlock);";

  @Override
  public Favorite create(Favorite entity) {

    KeyHolder keyHolder = new GeneratedKeyHolder();

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("userId", entity.getUserId());
    params.addValue("telId", entity.getTelId());
    params.addValue("favoriteBlock", entity.getFavoriteBlock());

    namedParameterJdbcTemplate.update(INSERT_FAVORITE, params, keyHolder);

    long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdRoleId);
  }

  private static final String CREATE_QUERY_UPDATE =
      "UPDATE favorite set user_id= :userId, tel_id= :telId, "
          + "favorite_block= :favoriteBlock where favorite_id= :favoriteId";

  @Override
  public Favorite update(Favorite entity) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("userId", entity.getUserId());
    params.addValue("telId", entity.getTelId());
    params.addValue("favoriteBlock", entity.getFavoriteBlock());

    params.addValue("favoriteId", entity.getFavoriteId());

    namedParameterJdbcTemplate.update(CREATE_QUERY_UPDATE, params);
    return findById(entity.getUserId());
  }
}
