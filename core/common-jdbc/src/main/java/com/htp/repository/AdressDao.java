package com.htp.repository;

import com.htp.domain.Adress;

import java.util.List;

public interface AdressDao extends GenericDao<Adress, Long> {

    List<Long> batchUpdate(List<Adress> adresses);

}
