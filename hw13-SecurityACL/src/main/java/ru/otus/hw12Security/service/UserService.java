package ru.otus.hw12Security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw12Security.model.User;
import ru.otus.hw12Security.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ru.otus.hw12Security.utils.Utils.toUserSet;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Set<User> users;

    public UserService(
            UserRepository userRepository,
            @Value("${users}") String[] users
    ) {
        this.userRepository = userRepository;
        this.users = toUserSet(users);
    }

    @PostConstruct
    void init() {
        userRepository.deleteAll();
        userRepository.saveAll(users);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByName(String username) {
        return userRepository.findByName(username);
    }
}
