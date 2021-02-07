package com.krishna;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishna.pojo.RequestPojo;
import com.krishna.pojo.TotalItemsQuantity;
import com.krishna.util.RequestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainTest {
    public   static ObjectMapper objectMapper = new ObjectMapper();
    String requestPayload = "{\n" +
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
    public RequestPojo requestBody;
    public TotalItemsQuantity totalItemsQuantity;
    Map<String, Map<String,Integer>> beveragesAndIngredientMap;
    BeverageMaker beverageMaker;

    @BeforeMethod
    public void setUp() throws Exception {
        try {
            requestBody =  objectMapper.readValue(requestPayload, RequestPojo.class);
        } catch (IOException except) {
            System.out.println("Exception Occurred while parsing the request Json");
        }

        totalItemsQuantity = RequestUtil.getItemsQuantityFromReq(requestBody);
        beveragesAndIngredientMap = RequestUtil.getBeveragesAndIngredientMap(requestBody);

        beverageMaker = BeverageMaker.INSTANCE;
        beverageMaker.initialize(RequestUtil.getOutLetFromRequestBody(requestBody));


    }

    @Test
    public void testMake() throws IOException {
        if(!beverageMaker.canMakeBeverages()){
            TotalItemsQuantity totalItemsQuantity = RequestUtil.getItemsQuantityFromReq(requestBody);
            beverageMaker.addIngredientsToMaker(totalItemsQuantity);
        }
        beverageMaker.make(beveragesAndIngredientMap);
        Queue<String> actualOutput = beverageMaker.resultList.getResults();
//
        final  Queue<String> expectedOutput = new ConcurrentLinkedQueue<String>();
        expectedOutput.add("hot_tea is prepared");
        expectedOutput.add("hot_coffee is prepared");
        expectedOutput.add("black_tea cannot be prepared because hot_water is not sufficient");
        expectedOutput.add("green_tea cannot be prepared because green_mixture is not available");
//
        Assert.assertEquals(actualOutput, expectedOutput);
    }
}
