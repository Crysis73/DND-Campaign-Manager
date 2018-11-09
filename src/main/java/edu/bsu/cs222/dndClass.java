package edu.bsu.cs222;


@SuppressWarnings({"SameParameterValue", "unused"})
class dndClass {
    private final String name;
    private final String description;
    private final Integer hitDice;
    private final Integer hitPointBonus;

    dndClass(String name, String description, @SuppressWarnings("SameParameterValue") Integer hitDice) {
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

    public Integer getHitDice() {

        return this.hitDice;
    }

    Integer getHitPointBonus(){

        return this.hitPointBonus;
    }

    public String toString(){
        return("Class :\n\t"+name+" - added 1d"+hitDice+" to Constitution to determine HP and adds an additional 1d"+hitDice+" per "+name+" level."+"\n\tDescription : "+description);
    }
}
