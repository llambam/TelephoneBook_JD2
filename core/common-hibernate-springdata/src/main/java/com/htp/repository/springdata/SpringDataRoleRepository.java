package com.htp.repository.springdata;

import com.htp.domain.HRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpringDataRoleRepository
    extends JpaRepository<HRole, Long>,
        CrudRepository<HRole, Long>,
        PagingAndSortingRepository<HRole, Long> {

  HRole findByUserRoleId(Long roleId);
}
