package com.htp.repository.hibernate;

import com.htp.domain.HTelephones;
import com.htp.repository.GenericDao;

import java.util.List;

public interface HibernateTelephones extends GenericDao<HTelephones, Long> {

  List<HTelephones> searchByName(String query, Integer limit, Integer offset);

  List<HTelephones> searchByTelephone(String query, Integer limit, Integer offset);
}
