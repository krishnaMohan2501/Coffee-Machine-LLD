package com.krishna.beverages;

import com.krishna.enums.BeverageType;
import com.krishna.enums.IngredientEnum;
import com.krishna.results.ResultList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class HotCoffee extends Beverages {

    ResultList resultList = ResultList.INSTANCE;
    List<String> hotCoffeeResult = new ArrayList<>();
    private int hotMilk;
    private int teaLeavesSyrup;
    public HotCoffee(int hotWater, int gingerSyrup, int sugarSyrup,  int hotMilk, int teaLeavesSyrup,
                     String type) {
        super(hotWater, gingerSyrup, sugarSyrup, type);
        this.hotMilk = hotMilk;
        this.teaLeavesSyrup = teaLeavesSyrup;
    }

    @Override
    public boolean canServe(Map<String, Integer> allIngredientWithValues) {

        if (this.getHotWater() > allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient())) {

            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    "is not " +
                    "sufficient");

        } else if (this.getGingerSyrup() > allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient())) {
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    "is not " +
                    "sufficient");

        } else if (this.getSugarSyrup() > allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient())) {
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    "is not " +
                    "sufficient");

        } else if (this.getTeaLeavesSyrup() > allIngredientWithValues.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient())) {
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.TEA_LEAVES_SYRUP.getIngredient() +
                    "is not " +
                    "sufficient");
        } else if (this.getTeaLeavesSyrup() > allIngredientWithValues.get(IngredientEnum.HOT_MILK.getIngredient())) {
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_MILK.getIngredient() +
                    "is not " +
                    "sufficient");
        }
        resultList.getResults().addAll(hotCoffeeResult);
        return hotCoffeeResult.isEmpty();

    }

    @Override
    public boolean isAllIngredientPresent(Map<String, Integer> allIngredientWithValues) {
        if(allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient()) == -1){
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    "is not " +
                    "available");
        }else if (allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient()) == -1) {
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    "is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient()) == -1){
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    "is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient()) == -1){
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.TEA_LEAVES_SYRUP.getIngredient() +
                    "is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.HOT_MILK.getIngredient()) == -1){
            hotCoffeeResult.add(BeverageType.HOT_COFFEE.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_MILK.getIngredient() +
                    "is not " +
                    "available");
        }
        resultList.getResults().addAll(hotCoffeeResult);
        return hotCoffeeResult.isEmpty();
    }
}
