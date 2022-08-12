package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User get(Long id);

    User findByUsername(String username);

    List<User> listUsers();

    void update(User user);

    void delete(Long id);
}
