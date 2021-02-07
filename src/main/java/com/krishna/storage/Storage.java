package com.krishna.storage;

import com.krishna.beverages.Beverages;

import java.util.Map;

public interface Storage{
    public void add(String key, Integer value);

    void updateBasedOnType(Beverages beverage);

    Map<String, Integer> getAll();

    int get(String key);

    void update(String key, Integer value);

    boolean checkIfDbEmpty();

}
