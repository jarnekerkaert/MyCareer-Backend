
package com.realdolmen.mycareer.roles;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
class RoleController {

    private final
    RoleService roleService;

    @Autowired
    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    List<Role> getRoles() {
        return roleService.findAll();
    }

    @RequestMapping(value = "/currentroles", method = RequestMethod.GET)
    List<Role> getCurrentRoles() {
        return roleService.findAllCurrentRoles();
    }

    @RequestMapping(value = "/prevroles", method = RequestMethod.GET)
    List<Role> getPreviousRoles() {
        return roleService.findAllPrevRoles();
    }

    @RequestMapping(value = "/employees/{id}/currentroles", method = RequestMethod.GET)
    List<Role> getCurrentRolesOfEmployee(@PathVariable("id") Long employeeId) {
        return roleService.findCurrentRoles(employeeId);
    }

//    @RequestMapping(value = "/employees/{id}/prevroles", method = RequestMethod.GET)
//    List<Role> getPreviousRolesOfEmployee(@PathVariable("id") Long employeeId) {
//        Employee emp = employeeService
//                .findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//        return roleService.findPrevRoles(employeeId);
//    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.DELETE)
    void deleteAllByEmployee(@PathVariable("id") Long id) {
        roleService.deleteByEmployeeId(id);
    }


    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.GET)
    List<Role> getRolesOfEmployee(@PathVariable("id") Long employeeId) {
        return roleService.findByEmployeeId(employeeId);
    }

    /*
    @RequestMapping(value = "/employees/{id}/role", method = RequestMethod.POST)
    Role postRole(@PathVariable("id") Long employeeId, @Valid @RequestBody Role role){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addRole(role);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return roleService.save(role);
    } */

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.POST)
    void postRoles(@PathVariable("id") Long employeeId, @RequestBody List<Role> roles) {
        roleService.deleteByEmployeeId(employeeId);
        saveRoles(roles, employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.PUT)
    void updateRoles(@PathVariable("id") Long employeeId, @Valid @RequestBody List<Role> roles) {
        //        try{
        roleService.deleteByEmployeeId(employeeId);
        saveRoles(roles, employeeId);
//        }catch(ConstraintViolationException|HttpServerErrorException e){
//            throw new ValidationException();
//        }
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    void deleteRole(@PathVariable("id") Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            roleService.deleteRole(role.get());
        } else {
            throw new ResourceNotFoundException("Functie", "id", id);
        }
    }

    private void saveRoles(List<Role> roles, Long employeeId) {
        roleService.saveRoles(roles.stream()
                .map(f -> {
                    f.setEmployeeId(employeeId);
                    return f;
                }).collect(Collectors.toList()));
    }
}
