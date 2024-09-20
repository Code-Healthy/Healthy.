package com.healthy.service;
import com.healthy.model.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User registerUser(User user);
}