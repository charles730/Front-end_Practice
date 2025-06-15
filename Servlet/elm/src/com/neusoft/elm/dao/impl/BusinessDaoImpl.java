package com.neusoft.elm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.DBUtil;

public class BusinessDaoImpl implements BusinessDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception {
        List<Business> list = new ArrayList<>();
        String sql = "select * from business where orderTypeId=? order by businessId";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, orderTypeId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Business business = new Business();
                setBusiness(business);
                list.add(business);
            }
        } finally {
            DBUtil.close(rs, pst);
        }
        return list;
    }

    private void setBusiness(Business business) throws SQLException {
        business.setBusinessId(rs.getInt("businessId"));
        business.setBusinessName(rs.getString("businessName"));
        business.setBusinessAddress(rs.getString("businessAddress"));
        business.setBusinessExplain(rs.getString("businessExplain"));
        business.setBusinessImg(rs.getString("businessImg"));
        business.setOrderTypeId(rs.getInt("orderTypeId"));
        business.setStarPrice(rs.getDouble("starPrice"));
        business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
        business.setRemarks(rs.getString("remarks"));
    }

    @Override
    public Business getBusinessById(Integer businessId) throws Exception {
        Business business = null;
        String sql = "select * from business where businessId=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, businessId);
            rs = pst.executeQuery();
            while (rs.next()) {
                business = new Business();
                setBusiness(business);
            }
        } finally {
            DBUtil.close(rs, pst);
        }
        return business;
    }

    @Override
    public List<Business> listStarBusinessById(String userId) throws Exception {
        List<Business> result = new ArrayList<>();
        List<Integer> businessIds = new ArrayList<>();
        String sql = "select businessId from userstar where userId = ?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) businessIds.add(rs.getInt(1));
            for (Integer businessId : businessIds) {
                result.add(this.getBusinessById(businessId));
            }
        } finally {
            DBUtil.close(rs, pst);
        }
        return result;
    }
}
