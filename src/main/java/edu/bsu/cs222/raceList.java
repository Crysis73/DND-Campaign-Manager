package edu.bsu.cs222;


class raceList {
    private final Race[] races;

    public raceList(){
        this.races = new Race[18];
        initializeRaces();
    }

    private void initializeRaces(){

        TraitMap DwarfTraits = new TraitMap();
        DwarfTraits.setAllValues(0);
        DwarfTraits.setValue("Constitution", 2);
        //------------------------------------------------
        TraitMap DeepGnomeTraits = new TraitMap();
        DeepGnomeTraits.setAllValues(0);
        DeepGnomeTraits.setValue("Intelligence",2);
        DeepGnomeTraits.setValue("Dexterity",1);
        //-------------------------------------------------
        TraitMap DragonbornTraits = new TraitMap();
        DragonbornTraits.setAllValues(0);
        DragonbornTraits.setValue("Strength",2);
        DragonbornTraits.setValue("Charisma",1);
        //-------------------------------------------------
        TraitMap ElfTraits = new TraitMap();
        ElfTraits.setAllValues(0);
        ElfTraits.setValue("Dexterity",2);
        //-------------------------------------------------
        TraitMap ForestGnomeTraits = new TraitMap();
        ForestGnomeTraits.setAllValues(0);
        ForestGnomeTraits.setValue("Intelligence",2);
        ForestGnomeTraits.setValue("Dexterity",1);
        //-------------------------------------------------
        TraitMap GnomeTraits = new TraitMap();
        GnomeTraits.setAllValues(0);
        GnomeTraits.setValue("Intelligence",2);
        //-------------------------------------------------
        TraitMap HalfElfTraits = new TraitMap();
        HalfElfTraits.setAllValues(0);
        //-------------------------------------------------
        TraitMap HalflingTraits = new TraitMap();
        HalflingTraits.setAllValues(0);
        HalflingTraits.setValue("Dexterity",2);
        //-------------------------------------------------
        TraitMap HalfOrcTraits = new TraitMap();
        HalfOrcTraits.setAllValues(0);
        HalfOrcTraits.setValue("Strength",2);
        HalfOrcTraits.setValue("Constitution",1);
        //-------------------------------------------------
        TraitMap HighElfTraits = new TraitMap();
        HighElfTraits.setAllValues(0);
        HighElfTraits.setValue("Dexterity",2);
        HighElfTraits.setValue("Intelligence",1);
        //-------------------------------------------------
        TraitMap HillDwarfTraits = new TraitMap();
        HillDwarfTraits.setAllValues(0);
        HillDwarfTraits.setValue("Constitution",2);
        HillDwarfTraits.setValue("Wisdom",1);
        //-------------------------------------------------
        TraitMap HumanTraits = new TraitMap();
        HumanTraits.setAllValues(1);
        //-------------------------------------------------
        TraitMap LightfootTraits = new TraitMap();
        LightfootTraits.setAllValues(0);
        LightfootTraits.setValue("Dexterity",2);
        LightfootTraits.setValue("Charisma",1);
        //-------------------------------------------------
        TraitMap MountainDwarfTraits = new TraitMap();
        MountainDwarfTraits.setAllValues(0);
        MountainDwarfTraits.setValue("Constitution",2);
        MountainDwarfTraits.setValue("Strength",2);
        //-------------------------------------------------
        TraitMap RockGnomeTraits = new TraitMap();
        RockGnomeTraits.setAllValues(0);
        RockGnomeTraits.setValue("Intelligence",2);
        RockGnomeTraits.setValue("Constitution",1);
        //-------------------------------------------------
        TraitMap StoutTraits = new TraitMap();
        StoutTraits.setAllValues(0);
        StoutTraits.setValue("Dexterity",2);
        StoutTraits.setValue("Constitution",1);
        //-------------------------------------------------
        TraitMap TieflingTraits= new TraitMap();
        TieflingTraits.setAllValues(0);
        TieflingTraits.setValue("Charisma",2);
        TieflingTraits.setValue("Intelligence",1);
        //-------------------------------------------------
        TraitMap WoodElfTraits = new TraitMap();
        WoodElfTraits.setAllValues(0);
        WoodElfTraits.setValue("Dexterity",2);
        WoodElfTraits.setValue("Wisdom",1);
        //-------------------------------------------------

        Race DeepGnome = new Race("Deep Gnome",DeepGnomeTraits);
        Race Dragonborn = new Race("Dragonborn",DragonbornTraits);
        Race Dwarf = new Race("Dwarf",DwarfTraits);
        Race Elf = new Race("Elf",ElfTraits);
        Race ForestGnome = new Race("Forest Gnome",ForestGnomeTraits);
        Race Gnome = new Race("Gnome",GnomeTraits);
        Race HalfElf = new Race("Half Elf",HalfElfTraits);
        Race Halfling = new Race("Halfling",HalflingTraits);
        Race HalfOrc = new Race("Half Orc",HalfOrcTraits);
        Race HighElf = new Race("High Elf",HighElfTraits);
        Race HillDwarf = new Race("Hill Dwarf",HillDwarfTraits);
        Race Human = new Race("Human",HumanTraits);
        Race Lightfoot = new Race("Lightfoot",LightfootTraits);
        Race MountainDwarf = new Race("Mountain Dwarf",MountainDwarfTraits);
        Race RockGnome = new Race("Rock Gnome",RockGnomeTraits);
        Race Stout = new Race("Stout",StoutTraits);
        Race Tiefling = new Race("Tiefling",TieflingTraits);
        Race WoodElf = new Race("Wood Elf",WoodElfTraits);
        races[0]=(DeepGnome);
        races[1]=(Dragonborn);
        races[2]=(Dwarf);
        races[3]=(Elf);
        races[4]=(ForestGnome);
        races[5]=(Gnome);
        races[6]=(HalfElf);
        races[7]=(Halfling);
        races[8]=(HalfOrc);
        races[9]=(HighElf);
        races[10]=(HillDwarf);
        races[11]=(Human);
        races[12]=(Lightfoot);
        races[13]=(MountainDwarf);
        races[14]=(RockGnome);
        races[15]=(Stout);
        races[16]=(Tiefling);
        races[17]=(WoodElf);
        initializeRaceDescriptions();
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
