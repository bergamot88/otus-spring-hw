package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.model.User;
import com.tokmakov.fuzzing_pantry.repository.UserRepository;
import com.tokmakov.fuzzing_pantry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveNew(String userName, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        return userRepository.save(new User(userName, encodePassword));
    }

    @Override
    public void authenticateUser(String userName, String password) {
        Optional<User> user = userRepository.findByUserName(userName);
        boolean isMatchUser = false;
        if (user.isPresent()) {
            isMatchUser = passwordEncoder.matches(password, user.get().getPassword());
        } else {
            throw  new EntityNotFoundException(String.format("User with name: %s not found", userName));
        }
        if (!isMatchUser) {
            throw new AuthenticationServiceException(String.format("Wrong passsword for user: %s", userName));
        }
    }
}
