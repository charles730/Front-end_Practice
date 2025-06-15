package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.Business;
import org.springframework.web.bind.annotation.*;

import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

public class BusinessController {
    @PostMapping("/listBusinessByOrderTypeId")
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

    @PostMapping("/starBusinessById")
    public Object starBusinessById(HttpServletRequest request) throws Exception {
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        String userId = request.getParameter("userId");
        Boolean reverse = Integer.valueOf(request.getParameter("reverse")) == 1;
        BusinessService service = new BusinessServiceImpl();
        return service.starBusinessById(businessId, userId, reverse);
    }

}