package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.dto.EmployeeModel;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "employees")
interface EmployeeClient {

    @RequestLine("GET /employees/{id}")
//    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    EmployeeModel getEmployee(@Param("id") Long id);
}
