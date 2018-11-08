package edu.bsu.cs222;

class dndClassList {
    private dndClass[] dndClasses;

    public dndClassList(){
        initializedndClasses();
    }

    private void initializedndClasses(){
        dndClass Barbarian = new dndClass("Barbarian","A fierce warrior of primitive background who can enter a battle rage",12);
        dndClass Bard = new dndClass("Bard","An inspiring magician whose power echoes the music of creation",8);
        dndClass Cleric = new dndClass("Cleric","A priestly champion who wields divine magic in service of a higher power",8);
        dndClass Druid = new dndClass("Druid","A priest of the Old Faith, wielding the powers of nature — moonlight and plant growth, fire and lightning — and adopting animal forms",8);
        dndClass Fighter = new dndClass("Fighter","A master of martial combat, skilled with a variety of weapons and armor",10);
        dndClass Monk = new dndClass("Monk","A master of martial arts, harnessing the power of the body in pursuit of physical and spiritual perfection",8);
        dndClass Paladin = new dndClass("Paladin","A holy warrior bound to a sacred oath",10);
        dndClass Ranger = new dndClass("Ranger","A warrior who uses martial prowess and nature magic to combat threats on the edges of civilization",10);
        dndClass Rogue = new dndClass("Rogue","A scoundrel who uses stealth and trickery to overcome obstacles and enemies",8);
        dndClass Sorcerer = new dndClass("Sorcerer","A spellcaster who draws on inherent magic from a gift or bloodline",6);
        dndClass Warlock = new dndClass("Warlock","A wielder of magic that is derived from a bargain with an extraplanar entity",8);
        dndClass Wizard = new dndClass("Wizard","A scholarly magic-user capable of manipulating the structures of reality",6);
        dndClasses = new dndClass[12];
        dndClasses[0]=Barbarian;
        dndClasses[1]=Bard;
        dndClasses[2]=Cleric;
        dndClasses[3]=Druid;
        dndClasses[4]=Fighter;
        dndClasses[5]=Monk;
        dndClasses[6]=Paladin;
        dndClasses[7]=Ranger;
        dndClasses[8]=Rogue;
        dndClasses[9]=Sorcerer;
        dndClasses[10]=Warlock;
        dndClasses[11]=Wizard;
    }

    dndClass[] getDndClasses(){
        return this.dndClasses;
    }
}
