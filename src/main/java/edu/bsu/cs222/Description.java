package edu.bsu.cs222;

class Description {
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
            result += "\n\tHeight : "+height;
        }
        if(weight!=null){
            result+= "\n\tWeight : "+weight;
        }
        if(age!=null){
            result+= "\n\tAge : "+age;
        }
        if(eyeColor!=null){
            result+="\n\tEye Color : "+eyeColor;
        }
        if(skinColor!=null){
            result+="\n\tSkin Color : "+skinColor;
        }
        if(additionalFeatures!=null){
            result+="\n\tAdditional Features : "+additionalFeatures;
        }
        if(alignment!=null){
            result+="\n\tAlignment : "+alignment;
        }
        if(languages!=null){
            result+="\n\tLanguages : "+languages;
        }
        if(exoticLanguages!=null){
            result+="\n\tExotic Languages : "+exoticLanguages;
        }
        if(personalityTrait1!=null){
            result+="\n\tPersonality Trait 1 : "+personalityTrait1;
        }
        if(personalityTrait2!=null){
            result+="\n\tPersonality Trait 2 : "+personalityTrait2;
        }
        if(ideals!=null){
            result+="\n\tIdeals : "+ideals;
        }
        if(bonds!=null){
            result+="\n\tBonds : "+bonds;
        }
        if(flaws!=null){
            result+="\n\tFlaws : "+flaws;
        }
        return result;
    }
}
