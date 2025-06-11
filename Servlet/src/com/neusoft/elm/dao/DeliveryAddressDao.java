package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.DeliveryAddress;

public interface DeliveryAddressDao {
    List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception;

    int saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;

    DeliveryAddress getDeliveryAddressById(Integer daId) throws Exception;

    int updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;

    int removeDeliveryAddress(Integer daId) throws Exception;
}
