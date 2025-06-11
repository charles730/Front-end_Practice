package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Orders;

public interface OrdersService {
    int createOrders(String userId, Integer businessId, Integer daId, Double orderTotal);

    Orders getOrdersById(Integer orderId);

    List<Orders> listOrdersByUserId(String userId);
}