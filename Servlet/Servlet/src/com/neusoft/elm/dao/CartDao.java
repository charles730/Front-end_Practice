package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Cart;

public interface CartDao {
    int saveCart(Cart cart) throws Exception;

    int updateCart(Cart cart) throws Exception;

    int removeCart(Cart cart) throws Exception;

    List<Cart> listCart(Cart cart) throws Exception;
}
