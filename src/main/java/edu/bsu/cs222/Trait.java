package edu.bsu.cs222;


public class Trait {
    private String traitName;
    private Integer value;

    public Trait(String traitName){
        this.traitName = traitName;
        Die d6 = new Die(6);
        this.value = d6.rollD6FourTimesDropLeast();
    }

    public void setValue(Integer value){

        this.value = value;
    }

    public String getName(){

        return this.traitName;
    }

    public Integer getValue(){

        return this.value;
    }

    public String toString(){

        return("Trait : " + this.traitName + " , Value : " + this.value);
    }
}
