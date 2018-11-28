package edu.bsu.cs222;


@SuppressWarnings({"unused"})
class dndClass {
    private final String name;
    private final String description;
    private final Integer hitDice;
    private final Integer hitPointBonus;

    dndClass(String name, String description, Integer hitDice) {
        this.name = name;
        this.description = description;
        this.hitDice = hitDice;
        Die die = new Die(hitDice);
        this.hitPointBonus = die.rollDie();
    }

    String getName() {
        return this.name;
    }

    public String getDescription() {

        return this.description;
    }

    Integer getHitDice() {

        return this.hitDice;
    }

    Integer getHitPointBonus(){

        return this.hitPointBonus;
    }

    public String toString(){
        return("Class :\n\t"+name+" - adds "+hitDice+" to Constitution modifier to determine HP and adds an additional 1d"+hitDice+" per "+name+" level."+"\n\tDescription : "+description);
    }
}
