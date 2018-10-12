package edu.bsu.cs222;

public class dndClass {
    private String name;
    private String description;
    private Integer hitDice; //number of dice you roll to determine how many HitPoints you gain per level. Will need to implement scaling for levels.
    private Integer hitPointsAtFirstLevel;

    /*
    Passing all arguments through the constructor because classes are static. We will need to manually create each class that
    users are able to choose from. These classes will have preset names, descriptions, and hitDice. The 'get' methods for name
    and description will probably never be used but I added them just in case.
     */

    public dndClass(String name, String description, Integer hitDice, Integer hitPointsAtFirstLevel) {
        this.name = name;
        this.description = description;
        this.hitDice = hitDice;
        this.hitPointsAtFirstLevel = hitPointsAtFirstLevel;
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
    public String toString(){
        return("Class : "+name+"\nDescription : "+description+" ")
    }
}
