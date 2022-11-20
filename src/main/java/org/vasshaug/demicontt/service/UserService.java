package org.vasshaug.demicontt.service;

import org.springframework.stereotype.Service;
import org.vasshaug.demicontt.repository.UserRepository;
import org.vasshaug.demicontt.domain.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> list() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void save(List<User> users) {
        userRepository.saveAll(users);
    }
}
