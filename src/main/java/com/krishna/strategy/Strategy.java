package com.krishna.strategy;

import com.krishna.pojo.TotalItemsQuantity;

import java.util.Map;

public interface Strategy {
    public boolean checkIfRefillNeeded(int misscount, int total);
}
