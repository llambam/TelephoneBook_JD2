package com.htp.repository.impl;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

  private static final String USER_ID = "user_id";
  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final String USER_NAME = "user_name";
  private static final String USER_SURNAME = "user_surname";
  private static final String USER_NUMBER = "user_number";
  private static final String REGISTER_DATE = "register_date";
  private static final String USER_BLOCK = "user_block";

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private User getUserRowMapper(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setUserId(resultSet.getLong(USER_ID));
    user.setLogin(resultSet.getString(LOGIN));
    user.setPassword(resultSet.getString(PASSWORD));
    user.setUserName(resultSet.getString(USER_NAME));
    user.setUserSurname(resultSet.getString(USER_SURNAME));
    user.setUserNumber(resultSet.getString(USER_NUMBER));
    user.setRegisterDate(resultSet.getTimestamp(REGISTER_DATE));
    user.setBlock(resultSet.getInt(USER_BLOCK));
    return user;
  }

  private static final String SELECT_ALL = "select * from user";

  @Override
  public List<User> findAll() {
    return namedParameterJdbcTemplate.query(SELECT_ALL, this::getUserRowMapper);
  }

  private static final String SELECT_ALL_BY_USER_ID = "select * from user where user_id = :userId";

  @Override
  public User findById(Long id) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("userId", id);

    return namedParameterJdbcTemplate.queryForObject(
        SELECT_ALL_BY_USER_ID, params, this::getUserRowMapper);
  }

  @Override
  public void delete(Long id) {}

  private static final String INSERT_USER =
      "INSERT INTO user (login, password, user_name, user_surname, user_number, register_date, user_block) "
          + "VALUES (:login, :password, :userName, :userSurname, :userNumber, :registerDate, :userBlock);";

  @Override
  public User create(User entity) {

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("login", entity.getLogin());
    params.addValue("password", entity.getPassword());
    params.addValue("userName", entity.getUserName());
    params.addValue("userSurname", entity.getUserSurname());
    params.addValue("userNumber", entity.getUserNumber());
    params.addValue("registerDate", entity.getRegisterDate());
    params.addValue("userBlock", entity.getBlock());

    namedParameterJdbcTemplate.update(INSERT_USER, params, keyHolder);

    long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdUserId);
  }

  private static final String CREATE_QUERY_UPDATE ="UPDATE user set login = :login, password= :password, " +
          "user_name = :userName, user_surname = :userSurname, user_number = :userNumber, register_date= :registerDate, user_block= :userBlock where user_id = :userId";


  @Override
  public User update(User entity) {


    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("login", entity.getLogin());
    params.addValue("password", entity.getPassword());
    params.addValue("userName", entity.getUserName());
    params.addValue("userSurname", entity.getUserSurname());
    params.addValue("userNumber", entity.getUserNumber());
    params.addValue("registerDate", entity.getRegisterDate());
    params.addValue("userBlock", entity.getBlock());

    params.addValue("userId", entity.getUserId());

    namedParameterJdbcTemplate.update(CREATE_QUERY_UPDATE, params);
    return findById(entity.getUserId());
  }


  private static final String FIND_BY_ID = "select * from user where login = :login";

  @Override
  public User findByLogin(String login) {

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("login", login);

    return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, params, this::getUserRowMapper);
  }


  @Override
  public List<Long> batchUpdate(List<User> users) {


    List<SqlParameterSource> batch = new ArrayList<>();
    for (User entity : users) {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("login", entity.getLogin());
      params.addValue("password", entity.getPassword());
      params.addValue("userName", entity.getUserName());
      params.addValue("userSurname", entity.getUserSurname());
      params.addValue("userNumber", entity.getUserNumber());
      params.addValue("registerDate", entity.getRegisterDate());
      params.addValue("userBlock", entity.getBlock());

      params.addValue("userId", entity.getUserId());
      batch.add(params);
  }
    namedParameterJdbcTemplate.batchUpdate(CREATE_QUERY_UPDATE, batch.toArray(new SqlParameterSource[batch.size()]));
    return users.stream().map(User::getUserId).collect(Collectors.toList());
  }

  public static final String SEARCH_QUERY =
      "select * from user where lower(user_name) LIKE lower(:query) or "
          + "lower(user_surname) LIKE lower(:query) limit :lim offset :off";

  @Override
  public List<User> search(String query, Integer limit, Integer offset) {

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("query", "%" + query + "%");
    params.addValue("lim", limit);
    params.addValue("off", offset);

    return namedParameterJdbcTemplate.query(SEARCH_QUERY, params, this::getUserRowMapper);
  }
}
