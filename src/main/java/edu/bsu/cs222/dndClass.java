package edu.bsu.cs222;

@SuppressWarnings("WeakerAccess")
public class dndClass {
    private final String name;
    private final String description;
    private final Integer hitDice; //number of dice you roll to determine how many HitPoints you gain per level. Will need to implement scaling for levels.
    private final Integer hitPointBonus;

    /*
    Passing all arguments through the constructor because classes are static. We will need to manually create each class that
    users are able to choose from. These classes will have preset names, descriptions, and hitDice. The 'get' methods for name
    and description will probably never be used but I added them just in case.
     */

    public dndClass(String name, String description, Integer hitDice) {
        this.name = name;
        this.description = description;
        this.hitDice = hitDice;
        Die die = new Die(hitDice);
        this.hitPointBonus = die.rollDie();
    }

    public String getName() {

        return this.name;
    }

    public String getDescription() {

        return this.description;
    }

    public Integer getHitDice() {

        return this.hitDice;
    }

    public Integer getHitPointBonus(){

        return this.hitPointBonus;
    }

    public String toString(){
        return("Class :\n\t"+name+" - added "+hitPointBonus+" to Constitution to determine HP and adds an additional 1d"+hitDice+" per "+name+" level."+"\n\tDescription : "+description);
    }
}
