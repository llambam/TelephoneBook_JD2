package com.htp.repository.impl;

import com.htp.domain.Telephones;
import com.htp.repository.TelephonesDao;
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
public class TelephonesDaoImpl implements TelephonesDao {


    private static final String TEL_ID = "tel_id";
    private static final String TEL_NAME = "tel_name";
    private static final String TEL_SURNAME = "tel_surname";
    private static final String TEL_NUMBER = "tel_number";
    private static final String TEL_CREATION_DATE = "tel_creation_date";
    private static final String TEL_BLOCK = "tel_block";
    private static final String USER_ID = "user_id";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private Telephones getTelRowMapper(ResultSet resultSet, int i) throws SQLException {
        Telephones telephones = new Telephones();
        telephones.setTelId(resultSet.getLong(TEL_ID));
        telephones.setTelName(resultSet.getString(TEL_NAME));
        telephones.setTelSurname(resultSet.getString(TEL_SURNAME));
        telephones.setTelNumber(resultSet.getString(TEL_NUMBER));
        telephones.setCreationDate(resultSet.getTimestamp(TEL_CREATION_DATE));
        telephones.setTelBlock(resultSet.getInt(TEL_BLOCK));
        telephones.setUserId(resultSet.getLong(USER_ID));
        return telephones;
    }

    private static final String SELECT_ALL = "select * from telephones";

    @Override
    public List<Telephones> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL, this::getTelRowMapper);
    }

    private static final String SELECT_ALL_BY_TEL_ID = "select * from telephones where tel_id = :telId";

    @Override
    public Telephones findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telId", id);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_ALL_BY_TEL_ID, params, this::getTelRowMapper);
    }

    @Override
    public void delete(Long id) {}

    private static final String INSERT_TEL ="INSERT INTO telephones (tel_name, tel_surname, tel_number, tel_creation_date, tel_block, user_id) "
            + "VALUES (:telName, :telSurname, :telNumber, :telCreationDate, :telBlock, :userId);";


    @Override
    public Telephones create(Telephones entity) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telName", entity.getTelName());
        params.addValue("telSurname", entity.getTelSurname());
        params.addValue("telNumber", entity.getTelNumber());
        params.addValue("telCreationDate", entity.getCreationDate());
        params.addValue("telBlock", entity.getTelBlock());
        params.addValue("userId", entity.getUserId());

        namedParameterJdbcTemplate.update(INSERT_TEL, params, keyHolder);

        long createdTelId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdTelId);
    }

    private static final String CREATE_QUERY_UPDATE ="UPDATE telephones set tel_name = :telName, tel_surname= :telSurname, tel_number = :telNumber, tel_creation_date= :telCreationDate, tel_block= :telBlock, user_id= :userId where tel_id = :telId";


    @Override
    public Telephones update(Telephones entity) {


        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telName", entity.getTelName());
        params.addValue("telSurname", entity.getTelSurname());
        params.addValue("telNumber", entity.getTelNumber());
        params.addValue("telCreationDate", entity.getCreationDate());
        params.addValue("telBlock", entity.getTelBlock());
        params.addValue("userId", entity.getUserId());

        params.addValue("telId", entity.getUserId());

        namedParameterJdbcTemplate.update(CREATE_QUERY_UPDATE, params);
        return findById(entity.getUserId());
    }


    private static final String FIND_BY_NUMBER = "select * from telephones where tel_number= :telNumber";


    @Override
    public Telephones findByTelephone(String login) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telNumber", login);

        return namedParameterJdbcTemplate.queryForObject(FIND_BY_NUMBER, params, this::getTelRowMapper);
    }

    @Override
    public List<Long> batchUpdate(List<Telephones> telephones) {


        List<SqlParameterSource> batch = new ArrayList<>();
        for (Telephones entity : telephones) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("telName", entity.getTelName());
            params.addValue("telSurname", entity.getTelSurname());
            params.addValue("telNumber", entity.getTelNumber());
            params.addValue("telCreationDate", entity.getCreationDate());
            params.addValue("telBlock", entity.getTelBlock());
            params.addValue("userId", entity.getUserId());

            params.addValue("telId", entity.getUserId());
            batch.add(params);
        }
        namedParameterJdbcTemplate.batchUpdate(CREATE_QUERY_UPDATE, batch.toArray(new SqlParameterSource[batch.size()]));
        return telephones.stream().map(Telephones::getUserId).collect(Collectors.toList());
    }

    public static final String SEARCH_QUERY =
            "select * from telephones where lower(tel_name) LIKE lower(:query) or "
                    + "lower(tel_surname) LIKE lower(:query) limit :lim offset :off";

    @Override
    public List<Telephones> search(String query, Integer limit, Integer offset) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(SEARCH_QUERY, params, this::getTelRowMapper);
    }
}
