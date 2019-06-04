package com.htp.repository.hibernate;

import com.htp.domain.hibernate.HTelephones;
import com.htp.repository.GenericDao;

import java.util.List;

public interface HibernateTelephones extends GenericDao<HTelephones, Long> {


    List<HTelephones> search(String query, Integer limit, Integer offset);
}
