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



////    @Test
////    public void testCharacterCreation(){
////        traitBonus humanconstitutionBonus = new traitBonus("Constitution",1);
////        traitBonus humanstrengthBonus = new traitBonus("Strength",1);
////        traitBonus humanwisdomBonus = new traitBonus("Wisdom",1);
////        traitBonus humanintelligenceBonus = new traitBonus("Intelligence",1);
////        traitBonus humancharismaBonus = new traitBonus("Charisma",1);
////        traitBonus humandexterityBonus = new traitBonus("Dexterity",1);
////        ArrayList<traitBonus> humanTraitBonuses= new ArrayList<>();
////        humanTraitBonuses.add(humanconstitutionBonus);
////        humanTraitBonuses.add(humanstrengthBonus);
////        humanTraitBonuses.add(humanwisdomBonus);
////        humanTraitBonuses.add(humanintelligenceBonus);
////        humanTraitBonuses.add(humancharismaBonus);
////        humanTraitBonuses.add(humandexterityBonus);
////
////
////        Race Human = new Race("Human",humanTraitBonuses,"This is a sample description");
////        dndClass Bard = new dndClass("Bard","This is a sample description",8);
////        Character Jack = new Character("Jack",Bard,Human);
////        Jack.setExperiencepoints(500);
////        Jack.setWealth(600);
////        System.out.println(Jack.toString());
////
////        traitBonus dwarfConstitutionBonus = new traitBonus("Constitution",2);
////        ArrayList<traitBonus> dwarfTraitBonuses = new ArrayList<>();
////        dwarfTraitBonuses.add(dwarfConstitutionBonus);
////        Race Dwarf = new Race("Dwarf",dwarfTraitBonuses,"This is a sample description");
////        dndClass Barbarian = new dndClass("Barbarian","This is a sample description",12);
////
////        Character Jill = new Character("Jill",Barbarian,Dwarf);
////        Jill.setExperiencepoints(300);
////        Jill.setWealth(2);
////        System.out.println(Jill.toString());
////    }
//
//    @Test
//    public void testTraitBonusFromRace(){
//        traitBonus constitutionBonus = new traitBonus("Constitution",1);
//        traitBonus strengthBonus = new traitBonus("Strength",1);
//        traitBonus wisdomBonus = new traitBonus("Wisdom",1);
//        traitBonus intelligenceBonus = new traitBonus("Intelligence",1);
//        traitBonus charismaBonus = new traitBonus("Charisma",1);
//        traitBonus dexterityBonus = new traitBonus("Dexterity",1);
//        ArrayList<traitBonus> humanTraitBonuses= new ArrayList<>();
//        humanTraitBonuses.add(constitutionBonus);
//        humanTraitBonuses.add(strengthBonus);
//        humanTraitBonuses.add(wisdomBonus);
//        humanTraitBonuses.add(intelligenceBonus);
//        humanTraitBonuses.add(charismaBonus);
//        humanTraitBonuses.add(dexterityBonus);
//        Trait[] traits = new Trait[6];
//        Trait strength = new Trait("Strength");
//        Trait dexterity = new Trait("Dexterity");
//        Trait constitution = new Trait("Constitution");
//        Trait intelligence = new Trait("Intelligence");
//        Trait wisdom = new Trait("Wisdom");
//        Trait charisma = new Trait("Charisma");
//        traits[0] = strength;
//        traits[1] = dexterity;
//        traits[2] = constitution;
//        traits[3] = intelligence;
//        traits[4] = wisdom;
//        traits[5] = charisma;
//        for(int i =0;i<humanTraitBonuses.size();i++){
//            String traitName = humanTraitBonuses.get(i).getTraitName();
//            for(int j=0;j<traits.length;j++){
//                String secondTraitName = traits[j].getName();
//                Integer valueBefore = traits[j].getValue();
//                if(traitName.equals(secondTraitName)){
//                    Integer replacementValue = humanTraitBonuses.get(i).getTraitBonusValue() + traits[j].getValue();
//                    traits[j].setValue(replacementValue);
//                    Integer valueAfter = traits[j].getValue();
//                    Assert.assertTrue(valueBefore<valueAfter && valueBefore!=valueAfter && (((valueAfter-valueBefore)==humanTraitBonuses.get(i).getTraitBonusValue())));
//
//                }
//
//            }
//        }
//    }
}
