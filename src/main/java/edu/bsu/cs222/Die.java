package edu.bsu.cs222;

import java.util.Random;

public class Die {
    private int size;

    public Die(int size){
        this.size = size;
    }

    public double rollDie(){
        Random randomNumberList = new Random();
        int result = randomNumberList.nextInt(size);
        return result+1;
    }

}