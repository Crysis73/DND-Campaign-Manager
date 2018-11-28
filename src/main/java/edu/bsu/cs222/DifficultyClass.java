package edu.bsu.cs222;

class DifficultyClass {
    private final String difficultyClassName;
    private final Integer difficultyClassValue;

    DifficultyClass(String difficultyClassName, Integer difficultyClassValue){
        this.difficultyClassName = difficultyClassName;
        this.difficultyClassValue = difficultyClassValue;
    }

    Integer getValue(){
        return difficultyClassValue;
    }

    String getName(){
        return this.difficultyClassName;
    }

    public String toString(){
        return difficultyClassName + " - " + difficultyClassValue;
    }
}
