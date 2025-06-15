package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

public class BusinessController {
    public Object listBusinessByOrderTypeId(HttpServletRequest request) throws Exception {
        Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
        BusinessService service = new BusinessServiceImpl();
        return service.listBusinessByOrderTypeId(orderTypeId);
    }

    @PostMapping("/getBusinessById")
    public Object getBusinessById(HttpServletRequest request) throws Exception {
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        BusinessService service = new BusinessServiceImpl();
        return service.getBusinessById(businessId);
    }
}