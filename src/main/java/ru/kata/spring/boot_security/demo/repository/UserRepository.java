package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository {

    User findByUsername(String username);

    void saveUser(User user);

    User get(Long id);

    List<User> listUsers();

    void update(User user);

    void delete(Long id);

}
