package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class testFinalProject {

    @Test
    public void testSetTrait(){
        Trait charisma = new Trait("Charisma");
        charisma.setValue(10);
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setTrait(characterSheet.getCharisma(),10);
        Trait valueAfter = characterSheet.getCharisma();
        Assert.assertEquals(charisma.toString(),valueAfter.toString());
    }
    @Test
    public void testCreateCharacter(){

    }

}
