package com.example.finance.service.impl;

import com.example.finance.model.entity.User;
import com.example.finance.repository.UserRepository;
import com.example.finance.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User userDto) {
        return userRepository.save(userDto);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User update(Long id, User userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDto.getName());
                    user.setEmail(userDto.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
