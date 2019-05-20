package com.htp.config.web;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import com.htp.repository.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipal;
import java.util.NoSuchElementException;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao usersRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            User user = usersRepository.findByLogin(login);
            return null;
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("User " + login + " not found.", e);
        }
    }

}