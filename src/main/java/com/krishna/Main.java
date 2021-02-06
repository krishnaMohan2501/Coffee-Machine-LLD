package com.krishna;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishna.pojo.RequestPojo;
import com.krishna.pojo.TotalItemsQuantity;
import com.krishna.util.BeverageUtil;
import com.krishna.util.RequestUtil;

import java.io.IOException;
import java.util.Map;

public class Main {
    private  static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        String requestBody = "{\n" +
                "  \"machine\": {\n" +
                "    \"outlets\": {\n" +
                "      \"count_n\": 3\n" +
                "    },\n" +
                "    \"total_items_quantity\": {\n" +
                "      \"hot_water\": 500,\n" +
                "      \"hot_milk\": 500,\n" +
                "      \"ginger_syrup\": 100,\n" +
                "      \"sugar_syrup\": 100,\n" +
                "      \"tea_leaves_syrup\": 100\n" +
                "    },\n" +
                "    \"beverages\": {\n" +
                "      \"hot_tea\": {\n" +
                "        \"hot_water\": 200,\n" +
                "        \"hot_milk\": 100,\n" +
                "        \"ginger_syrup\": 10,\n" +
                "        \"sugar_syrup\": 10,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"hot_coffee\": {\n" +
                "        \"hot_water\": 100,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"hot_milk\": 400,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"black_tea\": {\n" +
                "        \"hot_water\": 300,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"tea_leaves_syrup\": 30\n" +
                "      },\n" +
                "      \"green_tea\": {\n" +
                "        \"hot_water\": 100,\n" +
                "        \"ginger_syrup\": 30,\n" +
                "        \"sugar_syrup\": 50,\n" +
                "        \"green_mixture\": 30\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        RequestPojo requestObj = transformRequest(requestBody);

        BeverageMaker beverageMaker = BeverageMaker.INSTANCE;
        beverageMaker.assignOutletsToMaker(RequestUtil.getOutLetFromRequestBody(requestObj));

        if(!beverageMaker.canMakeBeverages()){
            TotalItemsQuantity totalItemsQuantity = RequestUtil.getItemsQuantityFromReq(requestObj);
            beverageMaker.addIngredientsToMaker(totalItemsQuantity);
        }
        Map<String, Map<String,Integer>> beveragesAndIngredientMap = RequestUtil.getBeveragesAndIngredientMap(requestObj);
        beverageMaker.make(beveragesAndIngredientMap);
        BeverageUtil.displayResult();

    }

    /**
     * Get the request object from Json
     * @param requestBody
     * @return
     */
    private static RequestPojo transformRequest(String requestBody) {
        RequestPojo requestObj = null;
        try {
            requestObj =  objectMapper.readValue(requestBody, RequestPojo.class);
        } catch (IOException except) {
            System.out.println("Exception Occurred while parsing the request Json");
        }
        return requestObj;
    }
}
