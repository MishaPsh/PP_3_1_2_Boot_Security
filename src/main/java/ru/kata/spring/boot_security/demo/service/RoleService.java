package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }


    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

    public void save(Role role) {
        roleRepository.saveAndFlush(role);
    }

}
