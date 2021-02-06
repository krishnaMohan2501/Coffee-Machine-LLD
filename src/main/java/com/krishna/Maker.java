package com.krishna;

import com.krishna.pojo.TotalItemsQuantity;

import java.util.Map;

/***
 * Maker Interface. Currently we have BeverageMaker in our System.
 * Later point of time if any new type of Maker comes then it can be easily handled by this interface.
 */
public interface Maker {

     void make(Map<String, Map<String, Integer>> requestParams);
     void addIngredientsToMaker(TotalItemsQuantity ingredients);
}
