package com.krishna.storage;

import com.krishna.beverages.Beverages;

import java.util.Map;

public interface Storage{
    public void add(String key, Integer value);

    void update(Beverages beverage);

    Map<String, Integer> get();

    boolean refill(String key);
}
