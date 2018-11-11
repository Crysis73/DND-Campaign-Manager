package edu.bsu.cs222;

class DifficultyClass {
    private final String difficultyClassName;
    private final Integer difficultyClassValue;

    DifficultyClass(String difficultyClassName, Integer difficultyClassValue){
        this.difficultyClassName = difficultyClassName;
        this.difficultyClassValue = difficultyClassValue;
    }

    public Integer getValue(){
        return difficultyClassValue;
    }

    public String toString(){
        return difficultyClassName;
    }
}
