package com.realdolmen.mycareer.PublicEmployee;

import com.realdolmen.mycareer.CommonLibrary.common.dto.EmployeeModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("employees")
public interface EmployeeClient {

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    EmployeeModel getEmployee(@PathVariable("id") Long id);

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    void updateEmployee(@PathVariable("id") Long id, EmployeeModel employeeModel);

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    void createEmployee(EmployeeModel employeeModel);
}
