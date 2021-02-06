package com.krishna;

import com.krishna.beverages.Beverages;
import com.krishna.pojo.TotalItemsQuantity;
import com.krishna.results.ResultList;
import com.krishna.storage.IngredientStorage;
import com.krishna.util.BeverageUtil;

import java.util.List;
import java.util.Map;

/**
 * Singleton class, since we only need to design a solution to run on single machine
 */
public class BeverageMaker implements Maker{

    private int noOfOutLets;
    private IngredientStorage storage;
    ResultList resultList = ResultList.INSTANCE;
    public static BeverageMaker INSTANCE = new BeverageMaker();
    public BeverageMaker(){
        storage = new IngredientStorage();
    }

    /**
     * Populate the Ingredients values to IN-Memory DB
     * @param ingredients
     */
    @Override
    public void addIngredientsToMaker(TotalItemsQuantity ingredients){
        storage.add(IngredientEnum.HOT_WATER.getIngredient(), ingredients.getHot_water());
        storage.add(IngredientEnum.HOT_MILK.getIngredient(), ingredients.getHot_milk());
        storage.add(IngredientEnum.GINGER_SYRUP.getIngredient(), ingredients.getGinger_syrup());
        storage.add(IngredientEnum.SUGAR_SYRUP.getIngredient(), ingredients.getSugar_syrup());
        storage.add(IngredientEnum.TEA_LEAVES_SYRUP.getIngredient(), ingredients.getTea_leaves_syrup());
    }

    /**
     * if beverage Maker is empty, means ingredients are empty then
     * we can not make any beverages
     * @return
     */
    public boolean canMakeBeverages(){
        return !storage.getIngredientsWithQuantity().isEmpty();
    }

    /**
     * Assign the no of outlets to beverage Maker
     * @param noOfOutLets
     */
    public void assignOutletsToMaker(int noOfOutLets){
        this.noOfOutLets = noOfOutLets;
    }

    /**
     * Function is responsible for making beverages
     * @param beveragesAndIngredientMap
     */
    @Override
    public void make(Map<String, Map<String, Integer>> beveragesAndIngredientMap){
        List<Beverages> beveragesObject = BeverageUtil.getBeveragesObject(beveragesAndIngredientMap);

        int totalBeverages = beveragesObject.size();
        // at a same time
        int noBatches = totalBeverages / noOfOutLets;
        int leftOut = totalBeverages % noOfOutLets;

        for(int i=0;i<noBatches;i++){
            int start = i*noOfOutLets;
            int end = start + noOfOutLets;
            List<Beverages> groupBeverages = beveragesObject.subList(start, end);
            serveBeverages(groupBeverages);
            System.out.println( " ");
        }

        if(leftOut > 0){
            List<Beverages> leftOutBeverages = beveragesObject.subList(totalBeverages-1,totalBeverages);
            serveBeverages(leftOutBeverages);
        }
    }

    private void serveBeverages(List<Beverages> beverages){

        for(Beverages beverage: beverages){
            Map<String, Integer> allIngredientValues = storage.get();
            if(beverage.isAllIngredientPresent(allIngredientValues) &&
                    beverage.canServe(allIngredientValues)){
                resultList.getResults().add(beverage.getType() + " " + "is prepared");
                storage.update(beverage);
            }
        }
    }
}
