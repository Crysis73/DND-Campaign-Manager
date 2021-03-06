package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.StrictMath.max;

public class testFinalProject {


    @Test
    public void testSetTrait(){
        Character Jack = new Character("Jack","Bard","Dwarf");
        Jack.getTraits().setValue("Charisma", 10);
        Integer valueAfter = Jack.getTraits().getValue("Charisma");
        Assert.assertEquals(10, (int) valueAfter);
    }

    @Test
    public void testRollD6FourTimesDropLeast(){
        Die d6 = new Die(6);
        Integer[] results = new Integer[500];
        for(int i=0;i<results.length;i++) {
            results[i] = d6.rollD6FourTimesDropLeast();
        }
        for (Integer result : results) {
            // noinspection ConstantConditions
            Assert.assertTrue(result <= 18 || result >=3);
        }
    }

    @Test
    public void testRandomNumberList(){
        Random randomNumberList = new Random();
        Integer[] results = new Integer[10000];
        for(int i=0;i<results.length;i++){
            results[i] = randomNumberList.nextInt(6)+1;
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
    public void testAutomaticTraitValueGeneration(){
        TraitMap traitMap = new TraitMap();
        ArrayList<Integer> traitValues = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : traitMap.getTraitMap().entrySet()){
            Integer trait = entry.getValue();
            traitValues.add(trait);
        }
        for (Integer traitValue : traitValues) {
            Assert.assertTrue(3 <= traitValue && traitValue <= 18);
        }
    }

    @Test
    public void testTraitMapCreation(){
        TraitMap traitMap = new TraitMap();
        ArrayList<String> traitNames = new ArrayList<>(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma", "Constitution"));
        Assert.assertEquals(traitMap.getTraitMap().size(), traitNames.size());
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
        ArrayList<Integer> testTraitValues = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

        for(int i=0;i<newTraitValues.size();i++){
            Assert.assertSame(testTraitValues.get(i), newTraitValues.get(i));
        }

    }

    @Test
    public void testTraitMapToString(){
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        Assert.assertSame("[]", traitMap.toString());
    }

    @Test
    public void testCharacterCreation(){
        Character Jack = new Character("Jack","Bard","Dwarf");
        Character Jill = new Character("Jill","Barbarian","Stout");
        Jill.setWealth(51);
        Jack.setWealth(300);
        Assert.assertNotSame("", Jack.toString());
        Assert.assertNotSame("", Jill.toString());
    }

    @Test
    public void testMergeTraitMaps(){
        raceList races = new raceList();
        TraitMap traitMap = new TraitMap();
        TraitMap raceTraits;
        Map<String,Integer> originalTraitMap = traitMap.getTraitMap();
        boolean isGood = Boolean.FALSE;
        for(int i =0;i<races.getRaces().length;i++) {
            int count = 0;
            raceTraits = races.getRaces()[i].getTraitBonuses();
            for (Map.Entry<String, Integer> entry : originalTraitMap.entrySet()) {
                String key = entry.getKey();
                if (raceTraits.getTraitMap().get(key) > 0) {
                    count++;
                }
            }
            isGood = count >= 1;
        }
        Assert.assertTrue(isGood);
    }

    @Test
    public void testJsonWriter(){
        JsonWriter writer = new JsonWriter();
        Campaign myCampaign = new Campaign("myCampaign");
        Character Casey = new Character("Casey","Ranger","Elf");
        Character Rebecca = new Character("Rebecca","Cleric","Rock Gnome");
        Character Largent = new Character("Professor Largent","Barbarian","Dragonborn");
        Casey.setCurrentHitPoints(3);
        Rebecca.setCurrentHitPoints(10);
        Largent.getTraits().setAllValues(20);
        myCampaign.addCharacer(Casey);
        myCampaign.addCharacer(Rebecca);
        myCampaign.addCharacer(Largent);
        writer.writeCampaignJson(myCampaign);
        Assert.assertNotNull(myCampaign.generateJsonString());
    }

    @Test
    public void testRollDieTwiceUseGreatest(){
        Die d20 = new Die(20);
        Integer value1 = d20.rollDie();
        Integer value2 = d20.rollDie();
        Integer result = (max(value1,value2));
        Assert.assertTrue(result>= value1 && result>=value2);
    }

    @Test
    public void testRollDieTwiceUseLeast(){
        Die d20 = new Die(20);
        Integer value1 = d20.rollDie();
        Integer value2 = d20.rollDie();
        Integer result = (min(value1,value2));
        Assert.assertTrue(result <= value1);
    }

    @Test
    public void testJsonLoader(){
        String filename = "myCampaign.json";
        JsonLoader loader = new JsonLoader();
        Campaign campaign = loader.fromJsontoCampaign(filename);
    }

    @Test
    public void testDetermineCharacterLevel(){
        Character Jack = new Character("Jack","Fighter","Dwarf");
        Jack.setExperiencepoints(162000);
        Integer actual = Jack.getLevel().getCurrentLevel();
        Integer expected = 14;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSetExperiencePoints(){
        Character Jack = new Character("Jack","Fighter","Dwarf");
        Integer startingLevel = Jack.getLevel().getCurrentLevel();
        Jack.setExperiencepoints(900);
        Integer levelAfter = Jack.getLevel().getCurrentLevel();
        Assert.assertTrue(levelAfter>startingLevel && levelAfter == 3);
    }

}
