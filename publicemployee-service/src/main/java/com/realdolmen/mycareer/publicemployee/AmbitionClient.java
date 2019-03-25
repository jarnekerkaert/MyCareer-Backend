package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.dto.AmbitionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("ambitions")
interface AmbitionClient {

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.GET)
    List<AmbitionModel> getAmbitionsEmployee(@PathVariable("id") Long id);

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.PUT)
    void updateAmbitions(@PathVariable("id") Long id, List<AmbitionModel> ambitions);

}
