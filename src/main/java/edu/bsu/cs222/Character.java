package edu.bsu.cs222;

import java.util.LinkedList;

public class Character {
    private CharacterSheet characterSheet;
    private LinkedList<Equipment> equipment;

    public void Character(){
        this.characterSheet = new CharacterSheet();
        this.equipment = new LinkedList<>();
    }

    public void addEquipment(Equipment item){
        equipment.add(item);
    }

}
