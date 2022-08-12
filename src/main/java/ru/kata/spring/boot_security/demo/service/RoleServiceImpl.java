package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService{

    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleRepository.add(role);
    }

    @Override
    public Role findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.getAll();
    }
}
