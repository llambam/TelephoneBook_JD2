package com.htp.repository.springdata;

import com.htp.domain.hibernate.HUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpringDataUserDao
    extends JpaRepository<HUser, Long>,
        CrudRepository<HUser, Long>,
        PagingAndSortingRepository<HUser, Long> {

  List<HUser> findByUserId(Long userId);
}
