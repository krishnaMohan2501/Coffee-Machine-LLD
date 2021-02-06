package com.krishna.beverages;
import com.krishna.BeverageType;
import com.krishna.IngredientEnum;
import com.krishna.results.ResultList;
import lombok.Getter;

import java.util.Map;

@Getter
public class BlackTea extends Beverages {

    ResultList resultList = ResultList.INSTANCE;
    private int teaLeavesSyrup;
    public BlackTea(int hotWater, int gingerSyrup, int sugarSyrup, int teaLeavesSyrup, String type) {
        super(hotWater, gingerSyrup, sugarSyrup, type);
        this.teaLeavesSyrup = teaLeavesSyrup;
    }

    @Override
   public boolean canServe(Map<String, Integer> allIngredientWithValues) {

        boolean isServable = true;
        if (this.getHotWater() > allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient())) {
            isServable = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getGingerSyrup() > allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient())) {
            isServable = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getSugarSyrup() > allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient())) {
            isServable = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getTeaLeavesSyrup() > allIngredientWithValues.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient())) {
            isServable = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.TEA_LEAVES_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");
        }
        return isServable;
    }

    @Override
    public boolean isAllIngredientPresent(Map<String, Integer> allIngredientWithValues) {

        boolean allPresent = true;
        if(allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient()) == -1){
            allPresent = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    " is not " +
                    "available");
        }else if (allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient()) == -1) {
            allPresent = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient()) == -1){
            allPresent = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient()) == -1){
            allPresent = false;
            resultList.getResults().add(BeverageType.HOT_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.TEA_LEAVES_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }

        return allPresent;
    }

}
