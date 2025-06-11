package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.service.OrdersService;
import com.neusoft.elm.service.impl.OrdersServiceImpl;

public class OrdersController {
    public Object createOrders(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        Double orderTotal = Double.valueOf(request.getParameter("orderTotal"));
        OrdersService service = new OrdersServiceImpl();
        return service.createOrders(userId, businessId, daId, orderTotal);
    }

    public Object getOrdersById(HttpServletRequest request) throws Exception {
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        OrdersService service = new OrdersServiceImpl();
        return service.getOrdersById(orderId);
    }

    public Object listOrdersByUserId(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        OrdersService service = new OrdersServiceImpl();
        return service.listOrdersByUserId(userId);
    }
}