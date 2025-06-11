package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.OrderDetailet;

public interface OrderDetailetDao {
    int saveOrderDetailetBatch(List<OrderDetailet> list) throws Exception;

    List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId) throws Exception;
}
