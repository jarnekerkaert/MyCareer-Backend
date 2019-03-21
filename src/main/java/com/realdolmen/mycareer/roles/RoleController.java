
package com.realdolmen.mycareer.roles;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.dto.RoleModel;
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
    public List<RoleModel> getRoles() {
        return roleService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.DELETE)
    public void deleteAllByEmployee(@PathVariable("id") Long id) {
        roleService.deleteByEmployeeId(id);
    }

    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.GET)
    public List<RoleModel> getRolesOfEmployee(@PathVariable("id") Long employeeId) {
        return roleService.findByEmployeeId(employeeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.POST)
    public void postRoles(@PathVariable("id") Long employeeId, @RequestBody List<RoleModel> roles) {
        saveRoles(roles.stream().map(this::convertToRole).collect(Collectors.toList()), employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.PUT)
    public void updateRoles(@PathVariable("id") Long employeeId, @Valid @RequestBody List<RoleModel> roles) {
        roleService.deleteByEmployeeId(employeeId);
        saveRoles(roles.stream().map(this::convertToRole).collect(Collectors.toList()), employeeId);
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
                .peek(f -> f.setEmployeeId(employeeId)).collect(Collectors.toList()));
    }

    private RoleModel convertToDTO(Role role) {
        RoleModel model = new RoleModel();
        model.setDescription(role.getDescription());
        model.setEmployeeId(role.getEmployeeId());
        model.setId(role.getId());
        model.setStart(role.getStart());
        model.setEnding(role.getEnding());
        model.setTitle(role.getTitle());
        return model;
    }

     private Role convertToRole(RoleModel model) {
        Role role = new Role();
        role.setTitle(model.getTitle());
        role.setDescription(model.getDescription());
        role.setStart(model.getStart());
        role.setEnding(model.getEnding());
        return role;
    }
}
