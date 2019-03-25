package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.dto.RoleModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("roles")
interface RoleClient {

    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.GET)
    List<RoleModel> getRolesOfEmployee(@PathVariable("id") Long id);

    @RequestMapping(value = "/employees/{id}/roles", method = RequestMethod.PUT)
    void updateRoles(@PathVariable("id") Long id, List<RoleModel> roles);

}
