package edu.bsu.cs222;

import java.util.LinkedList;

@SuppressWarnings("WeakerAccess")
public class DiceRoll {
    private final LinkedList<Die> diceRollList;

    public DiceRoll(int numberOfDice, int diceSize){
        LinkedList<Die> diceRollList = new LinkedList<>();
        for(int i=0;i<numberOfDice;i++){
            Die die = new Die(diceSize);
            diceRollList.add(die);
        }

        this.diceRollList = diceRollList;

    }

    public Integer getDiceRollResult(){
        Integer result = 0;
        for(int i=0;i<=diceRollList.size()-1;i++){
            result = result + diceRollList.get(i).rollDie();
        }
        return result;
    }


}
