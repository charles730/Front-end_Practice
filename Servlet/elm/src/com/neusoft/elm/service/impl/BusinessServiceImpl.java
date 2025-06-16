package com.neusoft.elm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.dao.UserDao;
import com.neusoft.elm.dao.impl.UserDaoImpl;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.util.DBUtil;

public class BusinessServiceImpl implements BusinessService {
    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
        List<Business> list = new ArrayList<>();
        BusinessDao dao = new BusinessDaoImpl();
        try {
            DBUtil.getConnection();
            list = dao.listBusinessByOrderTypeId(orderTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return list;
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        Business business = null;
        BusinessDao dao = new BusinessDaoImpl();
        try {
            DBUtil.getConnection();
            business = dao.getBusinessById(businessId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return business;
    }

    @Override
    public Integer starBusinessById(Integer businessId, String userId) {
        UserDao dao = new UserDaoImpl();
        int ret = 0;
        try {
            DBUtil.getConnection();
            ret = dao.starBusinessById(businessId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return ret;
    }

    @Override
    public Integer unstarBusinessById(Integer businessId, String userId) {
        UserDao dao = new UserDaoImpl();
        int ret = 0;
        try {
            DBUtil.getConnection();
            ret = dao.unstarBusinessById(businessId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return ret;
    }

    @Override
    public Integer isBusinessStarredById(Integer businessId, String userId) {
        UserDao dao = new UserDaoImpl();
        int ret = 0;
        try {
            DBUtil.getConnection();
            ret = dao.isBusinessStarredById(businessId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return ret;
    }

    @Override
    public List<Business> listStarBusinessById(String userId) {
        List<Business> result = null;
        BusinessDao dao = new BusinessDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.listStarBusinessById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return result;
    }
}