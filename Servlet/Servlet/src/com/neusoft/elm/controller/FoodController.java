package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.service.FoodService;
import com.neusoft.elm.service.impl.FoodServiceImpl;

public class FoodController {
    public Object listFoodByBusinessId(HttpServletRequest request) throws Exception {
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        FoodService service = new FoodServiceImpl();
        return service.listFoodByBusinessId(businessId);
    }
}