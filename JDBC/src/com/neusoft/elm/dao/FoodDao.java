package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Food;

public interface FoodDao {
    List<Food> listFoodByBusinessId(Integer businessId);

    int saveFood(Food food);

    Food getFoodById(Integer foodId);

    int updateFood(Food food);

    int removeFood(Integer foodId);
}

