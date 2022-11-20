package org.vasshaug.demicontt.service;

import org.springframework.stereotype.Service;
import org.vasshaug.demicontt.repository.UserRepository;
import org.vasshaug.demicontt.domain.UserElement;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserElement> list() {
        return userRepository.findAll();
    }

    public UserElement save(UserElement user) {
        return userRepository.save(user);
    }

    public void save(List<UserElement> users) {
        userRepository.saveAll(users);
    }
}
