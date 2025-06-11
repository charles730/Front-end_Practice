package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Cart;

public interface CartService {
    int saveCart(Cart cart);

    int updateCart(Cart cart);

    int removeCart(Cart cart);

    List<Cart> listCart(Cart cart);
}