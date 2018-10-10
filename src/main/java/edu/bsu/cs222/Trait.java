package edu.bsu.cs222;


public class Trait {
    private String name;
    private Integer value;

    public Trait(String name){
        this.name = name;
        //set value equal to the sum of the three highest random d6 rolls
    }

    public void setValue(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
