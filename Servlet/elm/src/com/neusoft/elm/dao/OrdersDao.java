package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Orders;

public interface OrdersDao {
    int saveOrders(Orders orders) throws Exception;

    Orders getOrdersById(Integer orderId) throws Exception;

    List<Orders> listOrdersByUserId(String userId) throws Exception;

    int confirm
}