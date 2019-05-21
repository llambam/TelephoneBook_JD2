package com.htp.repository.impl;

import com.htp.domain.Role;
import com.htp.repository.RoleDao;
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
public class RoleDaoImpl implements RoleDao {

  private static final String USER_ROLE_ID = "user_role_id";
  private static final String USER_ID = "user_id";
  private static final String ROLE_NAME = "user_role";

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException {
    Role role = new Role();
    role.setUserRoleId(resultSet.getLong(USER_ROLE_ID));
    role.setUserId(resultSet.getLong(USER_ID));
    role.setUserRole(resultSet.getString(ROLE_NAME));
    return role;
  }

  private static final String SELECT_ALL_BY_USER_ID = "select * from role where user_id = ?";

  @Override
  public List<Role> getRolesByUserId(Long userId) {
    return jdbcTemplate.query(SELECT_ALL_BY_USER_ID, new Object[] {userId}, this::getRoleRowMapper);
  }

  private static final String SELECT_ALL = "select * from role";

  @Override
  public List<Role> findAll() {
    return namedParameterJdbcTemplate.query(SELECT_ALL, this::getRoleRowMapper);
  }

  private static final String SELECT_ALL_BY_ROLE_ID =
      "select * from role where user_role_id = :userRoleId";

  @Override
  public Role findById(Long id) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("userRoleId", id);

    return namedParameterJdbcTemplate.queryForObject(
        SELECT_ALL_BY_ROLE_ID, params, this::getRoleRowMapper);
  }

  @Override
  public void delete(Long id) {}

  private static final String INSERT_ROLE =
      "INSERT INTO role (user_role, user_id) VALUES (:userRole, :userId);";

  @Override
  public Role create(Role entity) {

    KeyHolder keyHolder = new GeneratedKeyHolder();

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("userRole", entity.getUserRole());
    params.addValue("userId", entity.getUserId());

    namedParameterJdbcTemplate.update(INSERT_ROLE, params, keyHolder);

    long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdRoleId);
  }

  @Override
  public Role update(Role entity) {
    return null;
  }
}
