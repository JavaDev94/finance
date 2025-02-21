package com.example.finance.service;

import com.example.finance.model.entity.User;

public interface UserService {
    User create(User userDto);
    User get(Long id);
    User update(Long id, User userDto);
}
