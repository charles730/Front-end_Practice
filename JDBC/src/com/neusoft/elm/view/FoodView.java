package com.neusoft.elm.view;

import java.util.List;

import com.neusoft.elm.po.Food;

public interface FoodView {
    List<Food> showFoodList(Integer businessId);

    void saveFood(Integer businessId);

    void updateFood(Integer businessId);

    void removeFood(Integer businessId);
}

