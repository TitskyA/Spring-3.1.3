package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;


public interface RoleService {

    void add(Role role);

    Role findByRoleName(String name);

    List<Role> getAll();

}
