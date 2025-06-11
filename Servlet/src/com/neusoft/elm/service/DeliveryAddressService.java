package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.DeliveryAddress;

public interface DeliveryAddressService {
    List<DeliveryAddress> listDeliveryAddressByUserId(String userId);

    int saveDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress getDeliveryAddressById(Integer daId);

    int updateDeliveryAddress(DeliveryAddress deliveryAddress);

    int removeDeliveryAddress(Integer daId);
}