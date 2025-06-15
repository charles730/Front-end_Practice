package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
    List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception;

    Business getBusinessById(Integer businessId) throws Exception;

    List<Business> listStarBusinessById(String userId) throws Exception;
}
