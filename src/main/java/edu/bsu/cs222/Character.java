package edu.bsu.cs222;

import java.util.LinkedList;

public class Character {
    private String name;
    private CharacterSheet characterSheet;
    private LinkedList<Equipment> equipment;

    public void Character(String name){
        this.name = name;
        this.characterSheet = new CharacterSheet();
        this.equipment = new LinkedList<>();
    }

    public void addEquipment(Equipment item){
        equipment.add(item);
    }
    //create CharacterSheet reference.
    public String toString(){
        String result = "";
        result+="Name : " + name + "\nEquipment : " + equipment + "\n"+characterSheet.toString();
        return result;
    }
}
