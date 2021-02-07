package com.krishna.storage;

import com.krishna.enums.BeverageType;
import com.krishna.enums.IngredientEnum;
import com.krishna.beverages.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
/*
    In memory based DB. providing storage for ingredients and their quantity
    Class is responsible for get, add and update the values in DB
 */
public class IngredientStorage implements Storage {


    private Map<String, Integer> ingredientsWithQuantity =   new HashMap<>();

    public IngredientStorage() {
    }
    @Override
    public void add(String key, Integer value) {
        ingredientsWithQuantity.put(key,value);
    }

    // update function -> to update storage based on beverage type
    @Override
    public void updateBasedOnType(Beverages beverage) {

        String type = beverage.getType();
        if(type.equalsIgnoreCase(BeverageType.BLACK_TEA.getBeverageType())){

            BlackTea blackTea = (BlackTea) beverage;
            int tea_leave_syp_db = ingredientsWithQuantity.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient());
            int tea_leaves_syp = blackTea.getTeaLeavesSyrup();
            ingredientsWithQuantity.put(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient(), tea_leave_syp_db - tea_leaves_syp);

        } else if(type.equalsIgnoreCase(BeverageType.GREEN_TEA.getBeverageType())){

            GreenTea greenTea = (GreenTea) beverage;
            int green_mix_db = ingredientsWithQuantity.get(IngredientEnum.GREEN_MIXTURE.getIngredient());
            ingredientsWithQuantity.put(IngredientEnum.SUGAR_SYRUP.getIngredient(), green_mix_db - greenTea.getGreenMixture());

        } else if(type.equalsIgnoreCase(BeverageType.HOT_TEA.getBeverageType())){

            HotTea hotTea = (HotTea) beverage;
            int hotMilk_db = ingredientsWithQuantity.get(IngredientEnum.HOT_MILK.getIngredient());
            int teaLeavesSyrup_db = ingredientsWithQuantity.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient());
            ingredientsWithQuantity.put(IngredientEnum.HOT_MILK.getIngredient(), hotMilk_db - hotTea.getHotMilk());
            ingredientsWithQuantity.put(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient(), teaLeavesSyrup_db - hotTea.getTeaLeavesSyrup());

        }else if(type.equalsIgnoreCase(BeverageType.HOT_COFFEE.getBeverageType())){

            HotCoffee hotCoffee= (HotCoffee) beverage;
            int hotMilk_db = ingredientsWithQuantity.get(IngredientEnum.HOT_MILK.getIngredient());
            int teaLeavesSyrup_db = ingredientsWithQuantity.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient());
            ingredientsWithQuantity.put(IngredientEnum.HOT_MILK.getIngredient(), hotMilk_db - hotCoffee.getHotMilk());
            ingredientsWithQuantity.put(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient(), teaLeavesSyrup_db - hotCoffee.getTeaLeavesSyrup());
        }
    }

    @Override
    public  Map<String, Integer> getAll() {
        Map<String, Integer> results = new HashMap<>();

        int hotWaterValue = ingredientsWithQuantity.getOrDefault(IngredientEnum.HOT_WATER.getIngredient(), -1);
        int hotMilkValue = ingredientsWithQuantity.getOrDefault(IngredientEnum.HOT_MILK.getIngredient(), -1);
        int gingerSyrupValue = ingredientsWithQuantity.getOrDefault(IngredientEnum.GINGER_SYRUP.getIngredient(), -1);
        int sugarSyrupValue = ingredientsWithQuantity.getOrDefault(IngredientEnum.SUGAR_SYRUP.getIngredient(), -1);
        int teaLeavesSyrupValue = ingredientsWithQuantity.getOrDefault(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient(),
                -1);
        int greenMixtureValue = ingredientsWithQuantity.getOrDefault(IngredientEnum.GREEN_MIXTURE.getIngredient(),
                -1);

        results.put(IngredientEnum.HOT_WATER.getIngredient(), hotWaterValue);
        results.put(IngredientEnum.HOT_MILK.getIngredient(), hotMilkValue);
        results.put(IngredientEnum.GINGER_SYRUP.getIngredient(), gingerSyrupValue);
        results.put(IngredientEnum.SUGAR_SYRUP.getIngredient(), sugarSyrupValue);
        results.put(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient(), teaLeavesSyrupValue);
        results.put(IngredientEnum.GREEN_MIXTURE.getIngredient(), greenMixtureValue);
        return results;
    }

    @Override
    public int get(String key) {
        return ingredientsWithQuantity.getOrDefault(key,-1);
    }

    @Override
    public void update(String key, Integer value) {
        ingredientsWithQuantity.put(key,value);
    }

    @Override
    public boolean checkIfDbEmpty() {
        return ingredientsWithQuantity.isEmpty();
    }

}
