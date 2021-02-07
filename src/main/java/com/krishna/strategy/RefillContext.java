package com.krishna.strategy;

import java.util.Map;

public class RefillContext {
    private Strategy strategy;

    public RefillContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public boolean executeStrategy(int misscount, int total) {
        return strategy.checkIfRefillNeeded(misscount, total);
    }
}
