package com.krishna.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishna.pojo.BeveragePojo;
import com.krishna.pojo.RequestPojo;
import com.krishna.pojo.TotalItemsQuantity;

import java.util.Map;

/**
 * Utility class for Request
 */
public class RequestUtil {

    private RequestUtil(){}

    private  static ObjectMapper objectMapper = new ObjectMapper();
    public static int getOutLetFromRequestBody( RequestPojo requestObj){
        return requestObj.getMachine().getOutlets().getCount_n();
    }

    public static TotalItemsQuantity getItemsQuantityFromReq(RequestPojo requestObj){
        return requestObj.getMachine().getTotal_items_quantity();
    }

    public static Map<String, Map<String, Integer>> getBeveragesAndIngredientMap(RequestPojo requestObj){
        BeveragePojo beveragesPojo = requestObj.getMachine().getBeverages();
       return objectMapper.convertValue(beveragesPojo, Map.class);
    }
}
