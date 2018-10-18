package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.*;

public class testFinalProject {

    @Test
    public void testSetTrait(){
        dndClass Bard = new dndClass("Bard","This is a test description",8);
        Character Jack = new Character("Jack",Bard,"Dwarf");
        Jack.getTraits().setValue("Charisma", 10);
        Integer valueAfter = Jack.getTraits().getValue("Charisma");
        Assert.assertTrue(valueAfter == 10);
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
    public void testSingleDieRoll(){
        Die d4 = new Die(4);
        Die d6 = new Die(6);
        Die d8 = new Die(8);
        Die d20 = new Die(20);
        Assert.assertTrue(1<=d4.rollDie() && d4.rollDie()<=4);
        Assert.assertTrue(1<=d6.rollDie() && d6.rollDie()<=6);
        Assert.assertTrue(1<=d8.rollDie() && d8.rollDie()<=8);
        Assert.assertTrue(1<=d20.rollDie() && d20.rollDie()<=20);
    }

    @Test
    public void testDiceRoll(){
        DiceRoll roll2Dice = new DiceRoll(2,6);
        Integer result = roll2Dice.getDiceRollResult();
        Assert.assertTrue(2<=result && result<=12);
    }

    @Test
    public void testAutomaticTraitValueGeneration(){
        TraitMap traitMap = new TraitMap();
        ArrayList<Integer> traitValues = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : traitMap.getTraitMap().entrySet()){
            Integer trait = entry.getValue();
            traitValues.add(trait);
        }
        for(int i=0;i<traitValues.size();i++){
            Assert.assertTrue(1<=traitValues.get(i) && traitValues.get(i)<=18);
        }
    }

    @Test
    public void testTraitMapCreation(){
        TraitMap traitMap = new TraitMap();
        ArrayList<String> traitNames = new ArrayList<>();
        traitNames.addAll(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma", "Constitution"));
        Assert.assertTrue(traitMap.getTraitMap().size() == traitNames.size());
    }

    @Test
    public void testSetTraitMapValues(){
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        ArrayList<Integer> newTraitValues = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : traitMap.getTraitMap().entrySet()){
            Integer trait = entry.getValue();
            newTraitValues.add(trait);
        }
        ArrayList<Integer> testTraitValues = new ArrayList<>();
        testTraitValues.addAll(Arrays.asList(0,0,0,0,0,0));

        for(int i=0;i<newTraitValues.size();i++){
            Assert.assertTrue(testTraitValues.get(i)==newTraitValues.get(i));
        }

    }

    @Test
    public void testDwarfClassTraitBonuses(){
        Dwarf dwarf = new Dwarf();
        TraitMap dwarfTraitMap = dwarf.getRaceTraitBonuses();
        ArrayList<Integer> dwarfTraitValues = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dwarfTraitMap.getTraitMap().entrySet()){
            Integer trait = entry.getValue();
            dwarfTraitValues.add(trait);
        }
        ArrayList<Integer> testTraitValues = new ArrayList<>();
        testTraitValues.addAll(Arrays.asList(0,0,0,0,0,2));
        for(int i=0;i<dwarfTraitValues.size();i++){
            Assert.assertTrue(testTraitValues.get(i)==dwarfTraitValues.get(i));
        }

    }

    @Test
    public void testTraitMapToString(){
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        LinkedList<String> compareList = new LinkedList<>();
        Assert.assertTrue(traitMap.toString()=="[]");
    }

    @Test
    public void testCharacterCreation(){
        dndClass Bard = new dndClass("Bard","This is a descrption",8);
        Character Jack = new Character("Jack",Bard,"Dwarf");
        Character Jill = new Character("Jill",Bard,"Stout");
        Jill.setWealth(51);
        Jack.setWealth(300);
        System.out.println(Jack.toString());
        System.out.println(Jill.toString());
    }

    @Test
    public void testMergeTraitMaps(){
        Map<String,Integer> traitMap = new HashMap<>();
        ArrayList<String> traitNames = new ArrayList<>();
        traitNames.addAll(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma", "Constitution"));
        Dwarf dwarf = new Dwarf();
        TraitMap traitMap1 = dwarf.getRaceTraitBonuses();
        for(Map.Entry<String,Integer> entry : traitMap.entrySet()){
            if(entry.getValue() != 0){
                Integer value = traitMap1.getTraitMap().get(entry.getKey()) + entry.getValue();
                traitMap.replace(entry.getKey(),value);
                Assert.assertTrue(traitMap.get(entry.getKey())==value);
            }
        }
}

    @Test
    public void testJsonWriter(){
        JsonWriter writer = new JsonWriter();
        Campaign myCampaign = new Campaign("myCampaign");
        dndClass Bard = new dndClass("Bard","This is a descrption",8);
        Character Jack = new Character("Jack",Bard,"Dwarf");
        Character Jill = new Character("Jill",Bard,"Rock Gnome");
        myCampaign.addCharacer(Jack);
        myCampaign.addCharacer(Jill);
        writer.writeCampaignJson(myCampaign);
        System.out.println(myCampaign.generateJsonString());
    }
}
