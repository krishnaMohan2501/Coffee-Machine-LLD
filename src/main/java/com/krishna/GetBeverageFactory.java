package com.krishna;

import com.krishna.beverages.*;
import com.krishna.enums.BeverageType;
import com.krishna.enums.IngredientEnum;

import java.util.Map;
import java.util.Objects;

/***
 * Factory Design Pattern
 *
 * This class is responsible for building objects based on different types of beverages.
 */
public class GetBeverageFactory {

    public static Beverages getBeverage(Map<String, Integer> input, BeverageType beverageType) {

        Beverages beverageObj = null;
        if (Objects.nonNull(beverageType)) {
            switch (beverageType) {
                case HOT_TEA:
                    beverageObj = new HotTea(input.get(IngredientEnum.HOT_WATER.getIngredient()),
                            input.get(IngredientEnum.GINGER_SYRUP.getIngredient()),
                            input.get(IngredientEnum.SUGAR_SYRUP.getIngredient()),
                            input.get(IngredientEnum.HOT_MILK.getIngredient()),
                            input.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient()),
                            beverageType.getBeverageType());
                    break;
                case HOT_COFFEE:
                    beverageObj = new HotCoffee(input.get(IngredientEnum.HOT_WATER.getIngredient()),
                            input.get(IngredientEnum.GINGER_SYRUP.getIngredient()),
                            input.get(IngredientEnum.SUGAR_SYRUP.getIngredient()),
                            input.get(IngredientEnum.HOT_MILK.getIngredient()),
                            input.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient()),
                            beverageType.getBeverageType());
                    break;
                case BLACK_TEA:
                    beverageObj = new BlackTea(input.get(IngredientEnum.HOT_WATER.getIngredient()),
                            input.get(IngredientEnum.GINGER_SYRUP.getIngredient()),
                            input.get(IngredientEnum.SUGAR_SYRUP.getIngredient()),
                            input.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient()),
                            beverageType.getBeverageType());
                    break;
                case GREEN_TEA:
                    beverageObj = new GreenTea(input.get(IngredientEnum.HOT_WATER.getIngredient()),
                            input.get(IngredientEnum.GINGER_SYRUP.getIngredient()),
                            input.get(IngredientEnum.SUGAR_SYRUP.getIngredient()),
                            input.get(IngredientEnum.GREEN_MIXTURE.getIngredient()),
                            beverageType.getBeverageType());
                    break;
                default:
                    System.out.println("Invalid/Unsupported beverage type");
            }
        }
        return beverageObj;
    }
}
