package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private String name;
    private String description; //Will need to be manually set for each race.
    private TraitMap traitBonus;

    /*
    Passing all arguments through the constructor because Races are static. We will need to manually create each Race that
    users are able to choose from. These Races will have preset names, traitBonuses, traitNames, and descriptions.
     */

    public Race(String name){
        this.name = name;
        if(name == "Dwarf"){
            Dwarf dwarf = new Dwarf();
            this.traitBonus = dwarf.getRaceTraitBonuses();
            this.description = dwarf.getRaceDescription();
        }
        else if (name == "Hill Dwarf"){
            HillDwarf hillDwarf = new HillDwarf();
            this.traitBonus = hillDwarf.getRaceTraitBonuses();
            this.description = hillDwarf.getRaceDescription();
        }
        else if (name == "Mountain Dwarf"){
            MountainDwarf mountainDwarf = new MountainDwarf();
            this.traitBonus = mountainDwarf.getRaceTraitBonuses();
            this.description = mountainDwarf.getRaceDescription();
        }
        else if (name == "Elf"){
            Elf elf = new Elf();
            this.traitBonus = elf.getRaceTraitBonuses();
            this.description = elf.getRaceDescription();
        }
        else if (name == "High Elf"){
            HighElf highElf = new HighElf();
            this.traitBonus = highElf.getRaceTraitBonuses();
            this.description = highElf.getRaceDescription();
        }
        else if (name == "Wood Elf"){
            WoodElf woodElf = new WoodElf();
            this.traitBonus = woodElf.getRaceTraitBonuses();
            this.description = woodElf.getRaceDescription();
        }
        else if (name == "Halfling"){
            Halfling halfling = new Halfling();
            this.traitBonus = halfling.getRaceTraitBonuses();
            this.description = halfling.getRaceDescription();
        }
        //subrace of Halfling
        else if (name == "Lightfoot"){
            Lightfoot lightfoot = new Lightfoot();
            this.traitBonus = lightfoot.getRaceTraitBonuses();
            this.description = lightfoot.getRaceDescription();
        }
        //subrace of Halfling
        else if (name == "Stout"){
            Stout stout = new Stout();
            this.traitBonus = stout.getRaceTraitBonuses();
            this.description = stout.getRaceDescription();
        }
        else if (name == "Human"){
            Human human = new Human();
            this.traitBonus = human.getRaceTraitBonuses();
            this.description = human.getRaceDescription();
        }
        else if (name == "Dragonborn"){
            Dragonborn dragonborn = new Dragonborn();
            this.traitBonus = dragonborn.getRaceTraitBonuses();
            this.description = dragonborn.getRaceDescription();
        }
        else if (name == "Gnome"){
            Gnome gnome = new Gnome();
            this.traitBonus = gnome.getRaceTraitBonuses();
            this.description = gnome.getRaceDescription();
        }
        else if (name == "Forest Gnome"){
            ForestGnome forestGnome = new ForestGnome();
            this.traitBonus = forestGnome.getRaceTraitBonuses();
            this.description = forestGnome.getRaceDescription();
        }
        else if (name == "Deep Gnome"){
            DeepGnome deepGnome = new DeepGnome();
            this.traitBonus = deepGnome.getRaceTraitBonuses();
            this.description = deepGnome.getRaceDescription();
        }
        else if (name == "Rock Gnome"){
            RockGnome rockGnome = new RockGnome();
            this.traitBonus = rockGnome.getRaceTraitBonuses();
            this.description = rockGnome.getRaceDescription();
        }
        //Half-Elf class needs to be written and decided on
        else if (name == "Half-Orc"){
            HalfOrc halfOrc = new HalfOrc();
            this.traitBonus = halfOrc.getRaceTraitBonuses();
            this.description = halfOrc.getRaceDescription();
        }
        else if (name == "Tiefling"){
            Tiefling tiefling = new Tiefling();
            this.traitBonus = tiefling.getRaceTraitBonuses();
            this.description = tiefling.getRaceDescription();
        }













    }

    public TraitMap getTraitBonuses(){
        return this.traitBonus;
    }

    public String toString(){
        String result = "Race : "+this.name+"\n\tDescription : "+this.description;
        ArrayList<String> traitNames = new ArrayList<>();
        traitNames.addAll(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma", "Constitution"));
        for(int i=0;i<traitNames.size();i++){
            result += "\n\tAdds "+traitBonus.getValue(traitNames.get(i))+" to "+traitNames.get(i);
        }
        return result;
    }

}
