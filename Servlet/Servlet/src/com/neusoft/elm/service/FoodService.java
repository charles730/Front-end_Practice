package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Food;

public interface FoodService {

    List<Food> listFoodByBusinessId(Integer businessId);
}