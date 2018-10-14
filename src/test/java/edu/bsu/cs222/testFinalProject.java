package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class testFinalProject {

    @Test
    public void testSetTrait(){
        Trait charisma = new Trait("Charisma");
        charisma.setValue(10);
        Race Dwarf = new Race("Dwarf","Constituion",2,"This is a sample description");
        dndClass Bard = new dndClass("Bard","This is a test description",8);
        Character Jack = new Character("Jack",Bard,Dwarf);
        Jack.getCharisma().setValue(10);
        Trait valueAfter = Jack.getCharisma();
        Assert.assertEquals(charisma.toString(),valueAfter.toString());
    }

    @Test
    public void testRollD6FourTimesDropLeast(){
        Die d6 = new Die(6);
        Integer[] results = new Integer[500];
        for(int i=0;i<results.length;i++) {
            results[i] = d6.rollD6FourTimesDropLeast();
        }
        for(int i=0;i<results.length;i++){
            Assert.assertTrue(results[i]<=18);
        }
    }

    @Test
    public void testRandomNumberList(){
        Random randomNumberList = new Random();
        Integer[] results = new Integer[10000];
        for(int i=0;i<results.length;i++){
            results[i] = randomNumberList.nextInt(7);
            if(results[i]==0){
                results[i] += randomNumberList.nextInt(7);
                if(results[i]==0){
                    results[i] += randomNumberList.nextInt(7);
                }
                    if(results[i]==0){
                        results[i] += randomNumberList.nextInt(7);
                    }
                        if(results[i]==0){
                            results[i] += randomNumberList.nextInt(7);
                        }
                            if(results[i]==0){
                                results[i] += randomNumberList.nextInt(7);
                            }
                                if(results[i]==0){
                                    results[i] += randomNumberList.nextInt(7);
                                }
                                    if(results[i]==0){
                                        results[i] += randomNumberList.nextInt(7);
                                        }
                                        if(results[i]==0){
                                            results[i] += randomNumberList.nextInt(7);
                                        }
            }
            Assert.assertTrue(1<= results[i] && results[i]<=6 );
        }
    }

    @Test
    public void testDieRoll(){
        Die d6 = new Die(6);
        Assert.assertTrue(1<=d6.rollDie() && d6.rollDie()<=6);
    }

    @Test
    public void testDiceRoll(){
        DiceRoll roll2Dice = new DiceRoll(2,6);
        Integer result = roll2Dice.getDiceRollResult();
        Assert.assertTrue(2<=result && result<=12);
    }

    @Test
    public void testAutomaticTraitValueGeneration(){
        Trait Diligence = new Trait("Diligence");
        Integer value = Diligence.getValue();
        Assert.assertTrue(1<=value && value<=18);
    }

    @Test
    public void testCharacterCreation(){
        Race Dwarf = new Race("Dwarf","Constitution",2,"This is a sample description");
        dndClass Bard = new dndClass("Bard","This is a test description",8);
        Character Jack = new Character("Jack",Bard,Dwarf);
        Jack.setExperiencepoints(500);
        Jack.setWealth(600);
        System.out.println(Jack.toString());
    }

}
