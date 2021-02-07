package com.krishna.beverages;

import com.krishna.enums.BeverageType;
import com.krishna.enums.IngredientEnum;
import com.krishna.results.ResultList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class GreenTea  extends Beverages {

    ResultList resultList = ResultList.INSTANCE;
    List<String> greenTeaResults = new ArrayList<>();
    private int greenMixture;
    public GreenTea(int hotWater, int gingerSyrup, int sugarSyrup, int greenMixture, String type) {
        super(hotWater, gingerSyrup, sugarSyrup, type);
        this.greenMixture = greenMixture;
    }

    @Override
    public boolean canServe(Map<String, Integer> allIngredientWithValues) {

        if (this.getHotWater() > allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient())) {
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getGingerSyrup() > allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient())) {
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getSugarSyrup() > allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient())) {
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    " is not " +
                    "sufficient");

        } else if (this.getGreenMixture() > allIngredientWithValues.get(IngredientEnum.GREEN_MIXTURE.getIngredient())) {
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GREEN_MIXTURE.getIngredient() +
                    " is not " +
                    "sufficient");
        }
        resultList.getResults().addAll(greenTeaResults);
        return greenTeaResults.isEmpty();
    }

    @Override
    public boolean isAllIngredientPresent(Map<String, Integer> allIngredientWithValues) {
        if(allIngredientWithValues.get(IngredientEnum.HOT_WATER.getIngredient()) == -1){
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.HOT_WATER.getIngredient() +
                    " is not " +
                    "available");
        }else if (allIngredientWithValues.get(IngredientEnum.GINGER_SYRUP.getIngredient()) == -1) {
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GINGER_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.SUGAR_SYRUP.getIngredient()) == -1){
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.SUGAR_SYRUP.getIngredient() +
                    " is not " +
                    "available");
        }else if(allIngredientWithValues.get(IngredientEnum.GREEN_MIXTURE.getIngredient()) == -1){
            greenTeaResults.add(BeverageType.GREEN_TEA.getBeverageType() + " " + "cannot be prepared because " + IngredientEnum.GREEN_MIXTURE.getIngredient() +
                    " is not " +
                    "available");
        }
        resultList.getResults().addAll(greenTeaResults);
        return greenTeaResults.isEmpty();
    }
}
