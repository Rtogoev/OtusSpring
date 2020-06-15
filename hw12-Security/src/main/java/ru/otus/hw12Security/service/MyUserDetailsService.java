package ru.otus.hw12Security.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import ru.otus.hw12Security.model.MyUserDetails;
import ru.otus.hw12Security.model.User;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userService.findByName(username);
        if (user.isPresent()) {
            return new MyUserDetails(user.get());
        }
        throw new HttpServerErrorException(HttpStatus.NOT_FOUND, String.format("%s not found", username));
    }
}
