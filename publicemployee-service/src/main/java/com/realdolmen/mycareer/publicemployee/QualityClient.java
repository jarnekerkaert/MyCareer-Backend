package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.commonlibrary.common.dto.QualityModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("qualities")
public interface QualityClient {

    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.GET)
    List<QualityModel> getAllQualitiesEmployee(@PathVariable("id") Long id);

    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.PUT)
    void updateQualities(@PathVariable("id") Long id, List<QualityModel> qualities);

}
