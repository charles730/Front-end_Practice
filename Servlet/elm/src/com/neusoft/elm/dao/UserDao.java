package com.neusoft.elm.dao;

import com.neusoft.elm.po.User;

public interface UserDao {
    User getUserByIdByPass(String userId, String password) throws Exception;

    int getUserById(String userId) throws Exception;

    int saveUser(User user) throws Exception;

    Integer starBusinessById(Integer businessId, String userId) throws Exception;

    Integer unstarBusinessById(Integer businessId, String userId) throws Exception;

    Integer isBusinessStarredById(Integer businessId, String userId) throws Exception;
}
