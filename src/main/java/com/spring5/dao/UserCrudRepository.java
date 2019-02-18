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
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Repository
public interface UserCrudRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name, Pageable pageable);
}
