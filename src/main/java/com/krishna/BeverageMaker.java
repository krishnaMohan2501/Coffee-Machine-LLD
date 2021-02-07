package com.krishna;

import com.krishna.beverages.Beverages;
import com.krishna.enums.IngredientEnum;
import com.krishna.pojo.TotalItemsQuantity;
import com.krishna.results.ResultList;
import com.krishna.storage.IngredientStorage;
import com.krishna.strategy.RefillContext;
import com.krishna.strategy.RefillDefaultStrategy;
import com.krishna.util.BeverageUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Singleton class, since we only need to design a solution to run on single machine
 */
public class BeverageMaker implements Maker{

    private int noOfOutLets;
    private IngredientStorage storage;
    public  ExecutorService executor;
    ResultList resultList = ResultList.INSTANCE;
    public static BeverageMaker INSTANCE = new BeverageMaker();
    RefillDefaultStrategy defaultStrategy;
    RefillContext context;


    public BeverageMaker(){}

    public void initialize(int noOfOutLets){
        storage = new IngredientStorage();
        this.noOfOutLets = noOfOutLets;
        this.executor = Executors.newFixedThreadPool(noOfOutLets);
        defaultStrategy = new RefillDefaultStrategy();
        context = new RefillContext(defaultStrategy);
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
     * Function is responsible for making beverages
     * @param beveragesAndIngredientMap
     */
    @Override
    public void make(Map<String, Map<String, Integer>> beveragesAndIngredientMap){
        List<Beverages> beveragesObject = BeverageUtil.getBeveragesObject(beveragesAndIngredientMap);
        executor.submit(new Runnable() {

            @Override
            public void run() {
                populateResultAndUpdateDB(beveragesObject);
            }
        });

        executor.shutdownNow();
    }

    private void populateResultAndUpdateDB(List<Beverages> beverages){

        int miss = 0;
        int total = beverages.size();
        for(Beverages beverage: beverages){
            Map<String, Integer> allIngredientValues = storage.getAll();
            if(beverage.isAllIngredientPresent(allIngredientValues) && beverage.canServe(allIngredientValues)){
                resultList.getResults().add(beverage.getType() + " " + "is prepared");

                // after making beverages we have to subtract the ingredients Quantity form DB
                int hotWater_db = storage.get(IngredientEnum.HOT_WATER.getIngredient());
                int gingerSyrup_db = storage.get(IngredientEnum.GINGER_SYRUP.getIngredient());
                int sugarSyp_db  = storage.get(IngredientEnum.SUGAR_SYRUP.getIngredient());

                storage.update(IngredientEnum.HOT_WATER.getIngredient(), hotWater_db - beverage.getHotWater());
                storage.update(IngredientEnum.GINGER_SYRUP.getIngredient(), gingerSyrup_db - beverage.getGingerSyrup());
                storage.update(IngredientEnum.SUGAR_SYRUP.getIngredient(), sugarSyp_db - beverage.getSugarSyrup());
                storage.updateBasedOnType(beverage);

            }else{
                miss++;
            }
        }

        BeverageUtil.displayResult();

        /**
         * Default strategy to check if refill is required or not.
         */
        if(context.executeStrategy(miss, total)){
            System.out.println("Kindly refill the machine");
        }
    }

    /**
     * if beverage Maker is empty, means ingredients are empty then
     * we can not make any beverages. Just for validation
     * @return
     */
    public boolean canMakeBeverages(){
        return !storage.checkIfDbEmpty();
    }
}
