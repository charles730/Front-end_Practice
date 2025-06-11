package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
    List<Business> listBusiness(String businessName, String businessAddress);

    int saveBusiness(String businessName);

    int removeBusiness(int businessId);

    Business getBusinessByIdByPass(Integer businessId, String password);

    Business getBusinessById(Integer businessId);

    int updateBusiness(Business business);

    int updateBusinessByPassword(Integer businessId, String password);
}
