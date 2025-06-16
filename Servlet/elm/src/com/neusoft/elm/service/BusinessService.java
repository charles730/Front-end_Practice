package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Business;

import javax.persistence.criteria.CriteriaBuilder;

public interface BusinessService {
    List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    Business getBusinessById(Integer businessId);

    Integer starBusinessById(Integer businessId, String userId) throws Exception;

    Integer unstarBusinessById(Integer businessId, String userId) throws Exception;

    Integer isBusinessStarredById(Integer businessId, String userId) throws Exception;

    List<Business> listStarBusinessById(String userId);
}
