package com.htp.repository.springdata;

import com.htp.domain.HUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpringDataUserRepository
    extends JpaRepository<HUser, Long>,
        CrudRepository<HUser, Long>,
        PagingAndSortingRepository<HUser, Long> {

  HUser findByUserId(Long userId);

  List<HUser> findAllByUserNameLike(String searchQuery);
}
