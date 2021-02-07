package com.krishna.enums;

public enum BeverageType {

    HOT_TEA("hot_tea"),
    HOT_COFFEE("hot_coffee"),
    BLACK_TEA("black_tea"),
    GREEN_TEA("green_tea");

    private String beverageType;
    private BeverageType(String beverageType) {
        this.beverageType = beverageType;
    }

    public String getBeverageType() {
        return beverageType;
    }

}