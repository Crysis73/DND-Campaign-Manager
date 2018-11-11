package edu.bsu.cs222;


class raceList {
    private final Race[] races;

    raceList(){
        this.races = new Race[18];
        initializeRaces();
    }

    private void initializeRaces(){
        initializeDeepGnome();
        initializeDwarf();
        initializeDragonborn();
        initializeElf();
        initializeForestGnome();
        initializeGnome();
        initializeHalfElf();
        initializeHalfling();
        initializeHalfOrc();
        initializeHighElf();
        initializeHillDwarf();
        initializeHuman();
        initializeLightfoot();
        initializeMountainDwarf();
        initializeRockGnome();
        initializeStout();
        initializeTiefling();
        initializeWoodElf();
        initializeRaceDescriptions();
    }

    private void initializeWoodElf(){
        TraitMap WoodElfTraits = new TraitMap();
        WoodElfTraits.setAllValues(0);
        WoodElfTraits.setValue("Dexterity",2);
        WoodElfTraits.setValue("Wisdom",1);
        Race WoodElf = new Race("Wood Elf",WoodElfTraits);
        races[17]=(WoodElf);
    }

    private void initializeTiefling(){
        TraitMap TieflingTraits= new TraitMap();
        TieflingTraits.setAllValues(0);
        TieflingTraits.setValue("Charisma",2);
        TieflingTraits.setValue("Intelligence",1);
        Race Tiefling = new Race("Tiefling",TieflingTraits);
        races[16]=(Tiefling);
    }

    private void initializeStout(){
        TraitMap StoutTraits = new TraitMap();
        StoutTraits.setAllValues(0);
        StoutTraits.setValue("Dexterity",2);
        StoutTraits.setValue("Constitution",1);
        Race Stout = new Race("Stout",StoutTraits);
        races[15]=(Stout);
    }

    private void initializeRockGnome(){
        TraitMap RockGnomeTraits = new TraitMap();
        RockGnomeTraits.setAllValues(0);
        RockGnomeTraits.setValue("Intelligence",2);
        RockGnomeTraits.setValue("Constitution",1);
        Race RockGnome = new Race("Rock Gnome",RockGnomeTraits);
        races[14]=(RockGnome);
    }

    private void initializeMountainDwarf(){
        TraitMap MountainDwarfTraits = new TraitMap();
        MountainDwarfTraits.setAllValues(0);
        MountainDwarfTraits.setValue("Constitution",2);
        MountainDwarfTraits.setValue("Strength",2);
        Race MountainDwarf = new Race("Mountain Dwarf",MountainDwarfTraits);
        races[13]=(MountainDwarf);
    }

    private void initializeLightfoot(){
        TraitMap LightfootTraits = new TraitMap();
        LightfootTraits.setAllValues(0);
        LightfootTraits.setValue("Dexterity",2);
        LightfootTraits.setValue("Charisma",1);
        Race Lightfoot = new Race("Lightfoot",LightfootTraits);
        races[12]=(Lightfoot);
    }

    private void initializeHuman(){
        TraitMap HumanTraits = new TraitMap();
        HumanTraits.setAllValues(1);
        Race Human = new Race("Human",HumanTraits);
        races[11]=(Human);
    }

    private void initializeHillDwarf(){
        TraitMap HillDwarfTraits = new TraitMap();
        HillDwarfTraits.setAllValues(0);
        HillDwarfTraits.setValue("Constitution",2);
        HillDwarfTraits.setValue("Wisdom",1);
        Race HillDwarf = new Race("Hill Dwarf",HillDwarfTraits);
        races[10]=(HillDwarf);
    }

    private void initializeHighElf(){
        TraitMap HighElfTraits = new TraitMap();
        HighElfTraits.setAllValues(0);
        HighElfTraits.setValue("Dexterity",2);
        HighElfTraits.setValue("Intelligence",1);
        Race HighElf = new Race("High Elf",HighElfTraits);
        races[9]=(HighElf);
    }

    private void initializeHalfOrc(){
        TraitMap HalfOrcTraits = new TraitMap();
        HalfOrcTraits.setAllValues(0);
        HalfOrcTraits.setValue("Strength",2);
        HalfOrcTraits.setValue("Constitution",1);
        Race HalfOrc = new Race("Half Orc",HalfOrcTraits);
        races[8]=(HalfOrc);
    }

    private void initializeHalfling(){
        TraitMap HalflingTraits = new TraitMap();
        HalflingTraits.setAllValues(0);
        HalflingTraits.setValue("Dexterity",2);
        Race Halfling = new Race("Halfling",HalflingTraits);
        races[7]=(Halfling);
    }

    private void initializeHalfElf(){
        TraitMap HalfElfTraits = new TraitMap();
        HalfElfTraits.setAllValues(0);
        Race HalfElf = new Race("Half Elf",HalfElfTraits);
        races[6]=(HalfElf);
    }

    private void initializeGnome(){
        TraitMap GnomeTraits = new TraitMap();
        GnomeTraits.setAllValues(0);
        GnomeTraits.setValue("Intelligence",2);
        Race Gnome = new Race("Gnome",GnomeTraits);
        races[5]=(Gnome);
    }

    private void initializeForestGnome(){
        TraitMap ForestGnomeTraits = new TraitMap();
        ForestGnomeTraits.setAllValues(0);
        ForestGnomeTraits.setValue("Intelligence",2);
        ForestGnomeTraits.setValue("Dexterity",1);
        Race ForestGnome = new Race("Forest Gnome",ForestGnomeTraits);
        races[4]=(ForestGnome);
    }

    private void initializeElf(){
        TraitMap ElfTraits = new TraitMap();
        ElfTraits.setAllValues(0);
        ElfTraits.setValue("Dexterity",2);
        Race Elf = new Race("Elf",ElfTraits);
        races[3]=(Elf);
    }

    private void initializeDragonborn(){
        TraitMap DragonbornTraits = new TraitMap();
        DragonbornTraits.setAllValues(0);
        DragonbornTraits.setValue("Strength",2);
        DragonbornTraits.setValue("Charisma",1);
        Race Dragonborn = new Race("Dragonborn",DragonbornTraits);
        races[1]=(Dragonborn);
    }

    private void initializeDwarf(){
        TraitMap DwarfTraits = new TraitMap();
        DwarfTraits.setAllValues(0);
        DwarfTraits.setValue("Constitution", 2);
        Race Dwarf = new Race("Dwarf",DwarfTraits);
        races[2]=(Dwarf);
    }

    private void initializeDeepGnome(){
        TraitMap DeepGnomeTraits = new TraitMap();
        DeepGnomeTraits.setAllValues(0);
        DeepGnomeTraits.setValue("Intelligence",2);
        DeepGnomeTraits.setValue("Dexterity",1);
        Race DeepGnome = new Race("Deep Gnome",DeepGnomeTraits);
        races[0]=(DeepGnome);
    }

    Race[] getRaces(){
        return this.races;
    }

    private void initializeRaceDescriptions(){
        for (Race race : races) {
            race.setDescription("https://www.dndbeyond.com/compendium/rules/basic-rules/races#" + race.getName().replace(" ", ""));
        }
    }

}
