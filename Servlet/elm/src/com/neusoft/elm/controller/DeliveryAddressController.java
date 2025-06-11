package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.service.impl.DeliveryAddressServiceImpl;

public class DeliveryAddressController {
    public Object listDeliveryAddressByUserId(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        return service.listDeliveryAddressByUserId(userId);
    }

    public Object saveDeliveryAddress(HttpServletRequest request) throws Exception {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setUserId(request.getParameter("userId"));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        return service.saveDeliveryAddress(deliveryAddress);
    }

    public Object getDeliveryAddressById(HttpServletRequest request) throws Exception {
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        return service.getDeliveryAddressById(daId);
    }

    public Object updateDeliveryAddress(HttpServletRequest request) throws Exception {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setDaId(Integer.valueOf(request.getParameter("daId")));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        return service.updateDeliveryAddress(deliveryAddress);
    }

    public Object removeDeliveryAddress(HttpServletRequest request) throws Exception {
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        return service.removeDeliveryAddress(daId);
    }
}
