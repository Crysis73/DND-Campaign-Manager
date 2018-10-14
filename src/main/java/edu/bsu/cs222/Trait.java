package edu.bsu.cs222;


public class Trait {
    private String name;
    private Integer value;

    public Trait(String name){
        this.name = name;
        Die d6 = new Die(6);
        this.value = d6.rollD6FourTimesDropLeast();
    }

    public void setValue(Integer value){
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public Integer getValue(){
        return this.value;
    }

    public String toString(){
        return("Trait : " + this.name + " , Value : " + this.value);
    }
}
