package com.krishna.util;

import com.krishna.BeverageType;
import com.krishna.GetBeverageFactory;
import com.krishna.beverages.Beverages;
import com.krishna.results.ResultList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for BeverageMaker
 */
public class BeverageUtil {

    private BeverageUtil(){}

    public static List<Beverages> getBeveragesObject(Map<String, Map<String, Integer>> inputObject){

        List<Beverages> beverages = new ArrayList<>();
        for (BeverageType type : BeverageType.values()) {
            Beverages beverageObj = GetBeverageFactory.getBeverage( inputObject.get(type.getBeverageType()), type);
            beverages.add(beverageObj);
        }
        return beverages;
    }

    public static void displayResult() {
        ResultList resultList = ResultList.INSTANCE;
        List<String> results = resultList.getResults();
        for(String result: results){
            System.out.println(result);
        }

    }
}
