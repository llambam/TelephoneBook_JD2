package com.htp.repository.impl;

import com.htp.domain.Adress;
import com.htp.repository.AdressDao;
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
public class AdressDaoImpl implements AdressDao {

  private static final String ADRESS_ID = "adress_id";
  private static final String COUNTRY = "country";
  private static final String CITY = "city";
  private static final String STREET = "street";
  private static final String HOUSE_NUMBER = "house_number";
  private static final String FLOOR = "floor";
  private static final String APARTMENT_NUMBER = "apartment_number";
  private static final String USER_ID = "user_id";
  private static final String TEL_ID = "tel_id";
  private static final String ADRESS_BLOCK = "adress_block";
  private static final String USERS_ADRESS = "users_adress";

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private Adress getAdressRowMapper(ResultSet resultSet, int i) throws SQLException {
    Adress adress = new Adress();
    adress.setAdressId(resultSet.getLong(ADRESS_ID));
    adress.setCountry(resultSet.getString(COUNTRY));
    adress.setCity(resultSet.getString(CITY));
    adress.setStreet(resultSet.getString(STREET));
    adress.setHouseNumber(resultSet.getString(HOUSE_NUMBER));
    adress.setFloor(resultSet.getLong(FLOOR));
    adress.setApartmentNumber(resultSet.getLong(APARTMENT_NUMBER));
    adress.setUserId(resultSet.getLong(USER_ID));
    adress.setTelId(resultSet.getLong(TEL_ID));
    adress.setAdressBlock(resultSet.getInt(ADRESS_BLOCK));
    adress.setUserAdress(resultSet.getBoolean(USERS_ADRESS));
    return adress;
  }

  private static final String SELECT_ALL = "select * from adress";

  @Override
  public List<Adress> findAll() {
    return namedParameterJdbcTemplate.query(SELECT_ALL, this::getAdressRowMapper);
  }

  private static final String SELECT_ALL_BY_ADRESS_ID =
      "select * from adress where adress_id = :adressId";

  @Override
  public Adress findById(Long id) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("adressId", id);

    return namedParameterJdbcTemplate.queryForObject(
        SELECT_ALL_BY_ADRESS_ID, params, this::getAdressRowMapper);
  }

  @Override
  public void delete(Long id) {}

  private static final String INSERT_ADRESS =
      "INSERT INTO adress (country, city, street, house_number, floor, apartment_number, user_id, tel_id, adress_block, users_adress) "
          + "VALUES (:country, :city, :street, :houseNumber, :floor, :apartmentNumber, :userId, :telId, :adressBlock, :usersAdress);";

  @Override
  public Adress create(Adress entity) {

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("country", entity.getCountry());
    params.addValue("city", entity.getCity());
    params.addValue("street", entity.getStreet());
    params.addValue("houseNumber", entity.getHouseNumber());
    params.addValue("floor", entity.getFloor());
    params.addValue("apartmentNumber", entity.getApartmentNumber());
    params.addValue("userId", entity.getUserId());
    params.addValue("telId", entity.getTelId());
    params.addValue("adressBlock", entity.getAdressBlock());
    params.addValue("usersAdress", entity.isUserAdress());

    namedParameterJdbcTemplate.update(INSERT_ADRESS, params, keyHolder);

    long createdAdressId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdAdressId);
  }

  private static final String CREATE_QUERY_UPDATE =
      "UPDATE adress set country = :coontry, city= :city, street= :street, house_number= :houseNumber, floor= :floor, apartment_number=:apartmentNumber, user_id= :userId, tel_id=:telId, adress_block=:adress_block, users_adress= :usersAdress where adress_id= :adressId";

  @Override
  public Adress update(Adress entity) {

    MapSqlParameterSource params = new MapSqlParameterSource();

    params.addValue("country", entity.getCountry());
    params.addValue("city", entity.getCity());
    params.addValue("street", entity.getStreet());
    params.addValue("houseNumber", entity.getHouseNumber());
    params.addValue("floor", entity.getFloor());
    params.addValue("apartmentNumber", entity.getApartmentNumber());
    params.addValue("userId", entity.getUserId());
    params.addValue("telId", entity.getTelId());
    params.addValue("adressBlock", entity.getAdressBlock());
    params.addValue("usersAdress", entity.isUserAdress());

    params.addValue("adressId", entity.getAdressId());

    namedParameterJdbcTemplate.update(CREATE_QUERY_UPDATE, params);
    return findById(entity.getUserId());
  }

  @Override
  public List<Long> batchUpdate(List<Adress> adresses) {

    List<SqlParameterSource> batch = new ArrayList<>();
    for (Adress entity : adresses) {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("country", entity.getCountry());
      params.addValue("city", entity.getCity());
      params.addValue("street", entity.getStreet());
      params.addValue("houseNumber", entity.getHouseNumber());
      params.addValue("floor", entity.getFloor());
      params.addValue("apartmentNumber", entity.getApartmentNumber());
      params.addValue("userId", entity.getUserId());
      params.addValue("telId", entity.getTelId());
      params.addValue("adressBlock", entity.getAdressBlock());
      params.addValue("usersAdress", entity.isUserAdress());

      params.addValue("adressId", entity.getAdressId());
      batch.add(params);
    }
    namedParameterJdbcTemplate.batchUpdate(
        CREATE_QUERY_UPDATE, batch.toArray(new SqlParameterSource[batch.size()]));
    return adresses.stream().map(Adress::getUserId).collect(Collectors.toList());
  }

  //  private static final String SEARCH_QUERY =
  //      "select * from adress where lower(tel_name) LIKE lower(:query) or "
  //          + "lower(tel_surname) LIKE lower(:query) limit :lim offset :off";

}
