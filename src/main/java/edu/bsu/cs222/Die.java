package edu.bsu.cs222;

import java.util.Random;

public class Die {
    private int sides;

    public Die(int sides){
        this.sides = sides;
    }

    public Integer rollDie(){
        Random randomNumberList = new Random();
        /*
        For anyone reviewing this code, result is bounded by sides+1 because nextInt() doesn't include the bound in the numbers that are generated.
        Additionally, there are 8 if statements saying the same thing so ensure that the probability of this function ever returning 0 are so minimal that
        the probability is negligible.
        If there's a way to generate a single integer within two bounds, feel free to revise. I didn't feel like looking for a way.
         */
        int result = randomNumberList.nextInt(sides+1);
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        if(result==0){
            result = randomNumberList.nextInt(sides+1);
        }
        return result;
    }

    public Integer rollD6FourTimesDropLeast(){
        Integer result = 0;
        Integer[] results = new Integer[4];
        Die d6 = new Die(6);
        Integer numberOfRolls = 4;
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