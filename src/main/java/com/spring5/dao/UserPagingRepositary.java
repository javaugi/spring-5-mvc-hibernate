/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.dao;

import com.spring5.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Repository
public interface UserPagingRepositary extends PagingAndSortingRepository<User, Long> {

    @Query("select p from Person p where p.name like ?1 order by name")
    List<User> findByName(String name);

    @Query("select p from Person p where p.id = ?1")
    User findOne(Long id);

    @Query("select p from Person")
    List<User> findAll();

    List<User> findAllByName(String name, Pageable pageable);
}
