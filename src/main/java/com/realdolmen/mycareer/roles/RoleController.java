
package com.realdolmen.mycareer.roles;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController {

    private final
    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.DELETE)
    public void deleteAllByEmployee(@PathVariable("id") Long id) {
        roleService.deleteByEmployeeId(id);
    }

    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.GET)
    public List<Role> getRolesOfEmployee(@PathVariable("id") Long employeeId) {
        return roleService.findByEmployeeId(employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.POST)
    public void postRoles(@PathVariable("id") Long employeeId, @RequestBody List<Role> roles) {
        saveRoles(roles, employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.PUT)
    public void updateRoles(@PathVariable("id") Long employeeId, @Valid @RequestBody List<Role> roles) {
        roleService.deleteByEmployeeId(employeeId);
        saveRoles(roles, employeeId);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.findById(id)
                .map(r -> {
                    roleService.deleteRole(r);
                    return r;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
    }

    private void saveRoles(List<Role> roles, Long employeeId) {
        roleService.saveRoles(roles.stream()
                .map(f -> {
                    f.setEmployeeId(employeeId);
                    return f;
                }).collect(Collectors.toList()));
    }
}
