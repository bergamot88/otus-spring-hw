package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    User saveNew(String userName, String password);
    void authenticateUser(String userName, String password);
}
