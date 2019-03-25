
package com.realdolmen.mycareer.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

class RoleService {

    private final RoleRepository repository;

    @Autowired
    RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    List<Role> findAll() {
        return repository.findAll();
    }

    List<Role> findAllCurrentRoles() {
        return repository.findAll().stream().filter(Role::isCurrent).collect(Collectors.toList());
    }

    List<Role> findAllPrevRoles() {
        return repository.findAll().stream().filter(f -> !f.isCurrent()).collect(Collectors.toList());
    }

    List<Role> findCurrentRoles(Long employeeId) {
        return repository.findByEmployeeId(employeeId).stream().filter(Role::isCurrent).collect(Collectors.toList());
    }

    List<Role> findPrevRoles(Long employeeId) {
        return repository.findByEmployeeId(employeeId).stream().filter(f -> !f.isCurrent()).collect(Collectors.toList());
    }

    List<Role> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Transactional
    void saveRoles(List<Role> roles) {
        repository.saveAll(roles);
    }

    Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    void deleteByEmployeeId(Long employeeId) {
        repository.deleteByEmployeeId(employeeId);
    }

    void deleteRole(Role role) {
        repository.delete(role);
    }

}
