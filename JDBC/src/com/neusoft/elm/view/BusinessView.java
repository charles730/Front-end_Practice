package com.neusoft.elm.view;

import com.neusoft.elm.po.Business;

public interface BusinessView {
    void listBusinessAll();

    void listBusiness();

    void saveBusiness();

    void removeBusiness();

    Business login();

    void showBusiness(Integer businessId);

    void editBusiness(Integer businessId);

    void updateBusinessByPassword(Integer businessId);
}
