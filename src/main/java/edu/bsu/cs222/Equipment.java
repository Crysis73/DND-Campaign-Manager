package edu.bsu.cs222;

public class Equipment {
    private String description;
    private Trait trait;
    private Integer traitBonus;
    private String name;
    //How to add trait bonus

    public Equipment(String name, Trait trait, Integer traitBonus, String description){
        this.name = name;
        this.traitBonus = traitBonus;
        this.description = description;
        this.name = name;
        trait.setValue( traitBonus +
                trait.getValue());
    }
}
