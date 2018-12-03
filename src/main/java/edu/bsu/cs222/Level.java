package edu.bsu.cs222;

class Level {
    private final Integer currentLevel;
    private final Integer levelUpAt;

    public Level(Integer currentLevel, Integer levelUpAt){
        this.currentLevel = currentLevel;
        this.levelUpAt = levelUpAt;
    }

    Integer getCurrentLevel(){
        return this.currentLevel;
    }

    Integer getLevelUpAt(){
        return this.levelUpAt;
    }
}
