package com.kunam.pustaka.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Found {} users", users.size());
        return users;
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public User updateUser(User user, Integer id) {

        var findUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getFullName() != null) {
            findUser.setFullName(user.getFullName());
        }
        if (user.getEmail() != null) {
            findUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            findUser.setPassword(user.getPassword());
        }

        return userRepository.save(findUser);
    }

    public Integer deleteUser(Integer id) {
        userRepository.deleteById(id);
        return id;
    }

}
