package com.krishna.beverages;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Parent class for all types of beverages.
 * Later if any new kind of beverages introduced in system can be handled without much modification.
 *
 * IS-A relationship with child
 */
@AllArgsConstructor
@Getter
public abstract class Beverages {

    // common ingredients for any beverages
    private int hotWater;
    private int gingerSyrup;
    private int sugarSyrup;
    private String type;

    abstract public boolean canServe(Map<String, Integer> allIngredientWithValues);
    abstract public boolean isAllIngredientPresent(Map<String, Integer> allIngredientWithValues);

}
