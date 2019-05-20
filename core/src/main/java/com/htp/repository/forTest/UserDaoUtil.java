package com.htp.repository.forTest;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Transactional(rollbackFor = DataIntegrityViolationException.class)
public class UserDaoUtil {

  @Autowired private UserDao userDao;

  public void testOperations() {
    userDao.create(
        new User(
            1l,
            "log",
            "pass",
            "name",
            "surname",
            "2525550",
            new Timestamp(new Date().getTime()),
            0));
  }
}
