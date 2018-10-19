package edu.bsu.cs222;

import java.util.Random;

//@SuppressWarnings("WeakerAccess")
class Die {
    private final int sides;

    Die(int sides){

        this.sides = sides;
    }

    Integer rollDie(){
        Random randomNumberList = new Random();
        /*

         */

        return randomNumberList.nextInt(sides)+1;
    }

    Integer rollD6FourTimesDropLeast(){
        Integer result = 0;
        Integer[] results = new Integer[4];
        Die d6 = new Die(6);
        int numberOfRolls = 4;
        for(int i=0;i<numberOfRolls;i++){
            results[i] = d6.rollDie();
        }
        for(int i=0;i<results.length;i++){
            for(int j=i+1;j<results.length;j++){
                Integer first = results[i];
                Integer second = results[j];
                if(first<second){
                    Integer temp = results[i];
                    results[i] = results[j];
                    results[j] = temp;
                }
            }
        }
        for(int i=0;i<results.length-1;i++){
            result += results[i];
        }
        return result;
    }
}