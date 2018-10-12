package edu.bsu.cs222;

public class Description {
    private String height, weight, age, eyeColor, skinColor, additionalFeatures, alignment, languages, exoticLanguages, personalityTrait1, personalityTrait2, ideals, bonds, flaws;

    /*

    Author: Casey Haskins

    All of the member variables of this class are initialized by member variables so that the when creating a character
    a player may be as detailed as they wish to be. Not all of these descriptors are necessary for game play, so we shouldn't
    require a player/

     */

    public void setHeight(String height){
        this.height = height;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setEyeColor(String eyeColor){
        this.eyeColor = eyeColor;
    }
    public void setSkinColor(String skinColor){
        this.skinColor = skinColor;
    }
    public void setAdditionalFeatures(String additionalFeatures){
        this.additionalFeatures = additionalFeatures;
    }
    public void setAlignment(String alignment){
        this.alignment = alignment;
    }
    public void setLanguages(String languages){
        this.languages = languages;
    }
    public void setExoticLanguages(String exoticLanguages){
        this.exoticLanguages = exoticLanguages;
    }
    public void setPersonalityTrait1(String personalityTrait1){
        this.personalityTrait1 = personalityTrait1;
    }
    public void setPersonalityTrait2(String personalityTrait2){
        this.personalityTrait2 = personalityTrait2;
    }
    public void setIdeals(String ideals){
        this.ideals = ideals;
    }
    public void setBonds(String bonds){
        this.bonds = bonds;
    }
    public void setFlaws(String flaws){
        this.flaws = flaws;
    }

    public String toString(){
        String result = "";
        if(height !=null){
            result += "Height : "+height;
        }
        if(weight!=null){
            result+= "\nWeight : "+weight;
        }
        if(age!=null){
            result+= "\nAge : "+age;
        }
        if(eyeColor!=null){
            result+="\nEye Color : "+eyeColor;
        }
        if(skinColor!=null){
            result+="\nSkin Color : "+skinColor;
        }
        if(additionalFeatures!=null){
            result+="\nAdditional Features : "+additionalFeatures;
        }
        if(alignment!=null){
            result+="\nAlignment : "+alignment;
        }
        if(languages!=null){
            result+="\nLanguages : "+languages;
        }
        if(exoticLanguages!=null){
            result+="\nExotic Languages : "+exoticLanguages;
        }
        if(personalityTrait1!=null){
            result+="\nPersonality Trait 1 : "+personalityTrait1;
        }
        if(personalityTrait2!=null){
            result+="\nPersonality Trait 2 : "+personalityTrait2;
        }
        if(ideals!=null){
            result+="\nIdeals : "+ideals;
        }
        if(bonds!=null){
            result+="\nBonds : "+bonds;
        }
        if(flaws!=null){
            result+="\nFlaws : "+flaws;
        }
        return result;
    }
}
