package com.spring5.service;

import java.util.List;

import com.spring5.model.User;

public interface UserService {
   void save(User user);

   List<User> list();
}
