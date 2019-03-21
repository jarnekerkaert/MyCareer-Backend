
package com.realdolmen.mycareer.roles;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.dto.RoleModel;
import com.realdolmen.mycareer.employees.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

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
        return roleService.findAll().stream().map(r -> convertToDTO(r)).collect(Collectors.toList());
    }

//    @RequestMapping(value = "/currentroles", method = RequestMethod.GET)
//    public List<Role> getCurrentRoles() {
//        return roleService.findAllCurrentRoles();
//    }
//
//    @RequestMapping(value = "/prevroles", method = RequestMethod.GET)
//    public List<Role> getPreviousRoles() {
//        return roleService.findAllPrevRoles();
//    }

//    @RequestMapping(value = "/employees/{id}/currentroles", method = RequestMethod.GET)
//    public List<Role> getCurrentRolesOfEmployee(@PathVariable("id") Long employeeId) {
//        return roleService.findCurrentRoles(employeeId);
//    }

//    @RequestMapping(value = "/employees/{id}/prevroles", method = RequestMethod.GET)
//    public List<Role> getPreviousRolesOfEmployee(@PathVariable("id") Long employeeId) {
//        Employee emp = employeeService
//                .findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//        return roleService.findPrevRoles(employeeId);
//    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.DELETE)
    public void deleteAllByEmployee(@PathVariable("id") Long id) {
        roleService.deleteByEmployeeId(id);
    }


    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.GET)
    public List<RoleModel> getRolesOfEmployee(@PathVariable("id") Long employeeId) {
        return roleService.findByEmployeeId(employeeId).stream().map(r -> convertToDTO(r)).collect(Collectors.toList());
    }

    /*
    @RequestMapping(value = "/employees/{id}/role", method = RequestMethod.POST)
    public Role postRole(@PathVariable("id") Long employeeId, @Valid @RequestBody Role role){
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
    public void postRoles(@PathVariable("id") Long employeeId, @RequestBody List<Role> roles) {
        roleService.deleteByEmployeeId(employeeId);
        saveRoles(roles, employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.PUT)
    public void updateRoles(@PathVariable("id") Long employeeId, @Valid @RequestBody List<RoleModel> roles) {
        //        try{
        roleService.deleteByEmployeeId(employeeId);
        saveRoles(roles.stream().map(r -> convertToRole(r)).collect(Collectors.toList()), employeeId);
//        }catch(ConstraintViolationException|HttpServerErrorException e){
//            throw new ValidationException();
//        }
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Long id) {
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
    
    private RoleModel convertToDTO(Role role) {
        RoleModel model = new RoleModel(role);
        return model;
    }
    
     private Role convertToRole(RoleModel model) {
        Role role = new Role();
//        role.setId(model.getId());
//        role.setEmployeeId(model.getEmployeeId());
        role.setTitle(model.getTitle());
        role.setDescription(model.getDescription());
        role.setStart(model.getStart());
        role.setEnding(model.getEnding());
        return role;
    }
}
