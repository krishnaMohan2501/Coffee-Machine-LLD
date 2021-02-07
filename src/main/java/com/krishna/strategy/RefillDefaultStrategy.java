package com.krishna.strategy;
/**
 * Using Strategy Design Pattern to refill BeverageMaker.
 * Currently it is default , Later we can extend our logic to any custom strategy to refill
 *
 * if miss count is less than 30% then indicate for refill.
 */
public class RefillDefaultStrategy implements Strategy{


    @Override
    public boolean checkIfRefillNeeded(int misscount, int total) {
        if((misscount / total)*100 < 30){
            return true;
        }

        return false;

    }
}
