package com.htp.repository;

import com.htp.domain.Telephones;

import java.util.List;

public interface TelephonesDao extends GenericDao<Telephones, Long> {
    Telephones findByTelephone(String login);

    List<Long> batchUpdate(List<Telephones> users);

    List<Telephones> search(String query, Integer limit, Integer offset);
}
