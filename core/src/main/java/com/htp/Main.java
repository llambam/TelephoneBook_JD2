package com.htp;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

public class Main {

  @Autowired private UserDao userDao;

  private void testSaveUser() {
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

  public Main() {}

  public static void main(String[] args) {

    //        SpringApplication.run(ApplicationStarter.class, args);
    //        ConfigurableApplicationContext context = new
    // AnnotationConfigApplicationContext(AppConfig.class);

    //        UserDao userDao = (UserDao) context.getBean("userDaoImpl");
    //        UserDaoUtil userDaoUtil = (UserDaoUtil) context.getBean("userDaoUtil");

    //        userDaoUtil.testOperations();

    //        ConfigurableApplicationContext context = new
    // AnnotationConfigApplicationContext(AppConfig.class);
    //
    //        UserDao userDao = (UserDao) context.getBean("userDaoImpl");
    //        UserDaoUtil userDaoUtil = (UserDaoUtil) context.getBean("userDaoUtil");
    //
    //        userDaoUtil.testOperations();
    //
    //        for (User user : userDao.findAll()) {
    //            System.out.println(user.toString());
    //        }
  }
}
