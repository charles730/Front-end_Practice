package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessService {
    List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    Business getBusinessById(Integer businessId);
}
