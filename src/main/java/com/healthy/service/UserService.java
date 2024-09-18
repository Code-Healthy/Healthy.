package com.healthy.service;

import com.healthy.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    User registerUser(User user);
}
