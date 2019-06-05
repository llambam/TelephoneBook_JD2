package com.htp.config;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserDao userDao;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    try {
      User user = userDao.findByLogin(login);
      return null;
    } catch (NoSuchElementException e) {
      throw new UsernameNotFoundException("User " + login + " not found.", e);
    }
  }
}
