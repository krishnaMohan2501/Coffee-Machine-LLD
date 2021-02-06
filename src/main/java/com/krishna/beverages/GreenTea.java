package com.krishna.beverages;

import com.krishna.BeverageType;
import com.krishna.IngredientEnum;
import com.krishna.results.ResultList;
import lombok.Getter;

import java.util.Map;

@Getter
public class GreenTea  extends Beverages {

    ResultList resultList = ResultList.INSTANCE;
    private int greenMixture;
    public GreenTea(int hotWater, int gingerSyrup, int sugarSyrup, int greenMixture, String type) {
        super(hotWater, gingerSyrup, sugarSyrup, type);
        this.greenMixture = greenMixture;
    }

    @Override
    public boolean canServe(Map<String, Integer> allIngredientWithValues) {

        boolean  isServable = true;
        if (this.getHotWater() > allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient())) {
            isServable = true;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getGingerSyrup() > allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient())) {
            isServable = true;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getSugarSyrup() > allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient())) {
            isServable = true;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getGreenMixture() > allIngredientWithValues.get(IngredientEnum.GREEN_MIXTURE.getIngredient())) {
            isServable = true;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GREEN_MIXTURE.getIngredient() +
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
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    " is not " +
                    "available");
        }else if (allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient()) == -1) {
            allPresent = false;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient()) == -1){
            allPresent = false;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.GREEN_MIXTURE.getIngredient()) == -1){
            allPresent = false;
            resultList.getResults().add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GREEN_MIXTURE.getIngredient() +
                    " is not " +
                    "available");
        }

        return allPresent;
    }
}
