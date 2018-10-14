package edu.bsu.cs222;

import java.util.LinkedList;

public class DiceRoll {
    private int numberOfDice;
    private LinkedList<Die> diceRollList;

    public DiceRoll(int numberOfDice, int diceSize){
        LinkedList<Die> diceRollList = new LinkedList<Die>();
        for(int i=0;i<numberOfDice;i++){
            Die die = new Die(diceSize);
            diceRollList.add(die);
        }

        this.diceRollList = diceRollList;
        this.numberOfDice = numberOfDice;

    }

    public Integer getDiceRollResult(){
        Integer result = 0;
        for(int i=0;i<=diceRollList.size()-1;i++){
            result = result + diceRollList.get(i).rollDie();
        }
        return result;
    }


}
