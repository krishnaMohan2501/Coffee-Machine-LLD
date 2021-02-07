package com.krishna.enums;

public enum IngredientEnum {
    HOT_WATER("hot_water"),
    GINGER_SYRUP("ginger_syrup"),
    HOT_MILK("hot_milk"),
    SUGAR_SYRUP("sugar_syrup"),
    GREEN_MIXTURE("green_mixture"),
    TEA_LEAVES_SYRUP("tea_leaves_syrup");

    private String ingredient;
    private IngredientEnum(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

}