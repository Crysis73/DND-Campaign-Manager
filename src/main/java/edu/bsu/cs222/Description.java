package edu.bsu.cs222;

class Description {
    private String height, weight, age, eyeColor, skinColor, additionalFeatures, alignment, languages, exoticLanguages, personalityTrait1, personalityTrait2, ideals, bonds, flaws;

    void setHeight(String height){
        this.height = height;
    }

    void setWeight(String weight){
        this.weight = weight;
    }

    void setAge(String age){
        this.age = age;
    }

    void setEyeColor(String eyeColor){
        this.eyeColor = eyeColor;
    }

    void setSkinColor(String skinColor){
        this.skinColor = skinColor;
    }

    public void setAdditionalFeatures(String additionalFeatures){
        this.additionalFeatures = additionalFeatures;
    }

    void setAlignment(String alignment){
        this.alignment = alignment;
    }

    void setLanguages(String languages){
        this.languages = languages;
    }

    public void setExoticLanguages(String exoticLanguages){
        this.exoticLanguages = exoticLanguages;
    }

    public void setPersonalityTrait1(String personalityTrait1){
        this.personalityTrait1 = personalityTrait1;
    }

    void setPersonalityTrait2(String personalityTrait2){
        this.personalityTrait2 = personalityTrait2;
    }

    public void setIdeals(String ideals){
        this.ideals = ideals;
    }

    public void setBonds(String bonds){
        this.bonds = bonds;
    }

    void setFlaws(String flaws){
        this.flaws = flaws;
    }

    void setAllValues(String height, String weight, String age, String eyeColor, String skinColor, String additionalFeatures,
                      String alignment, String languages, String exoticLanguages, String personalityTrait1,
                      String personalityTrait2, String ideals, String bonds, String flaws){
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.eyeColor = eyeColor;
        this.skinColor = skinColor;
        this.additionalFeatures = additionalFeatures;
        this.alignment = alignment;
        this.languages = languages;
        this.exoticLanguages = exoticLanguages;
        this.personalityTrait1 = personalityTrait1;
        this.personalityTrait2 = personalityTrait2;
        this.ideals = ideals;
        this.bonds = bonds;
        this.flaws = flaws;

    }
    String getHeight(){
        return this.height;
    }
    String getWeight(){
        return this.weight;
    }
    String getAge(){
        return this.age;
    }
    String getEyeColor(){
        return this.eyeColor;
    }
    String getSkinColor(){
        return this.skinColor;
    }
    String getAdditionalFeatures(){
        return this.additionalFeatures;
    }
    String getAlignment(){
        return this.alignment;
    }
    String getLanguages(){
        return this.languages;
    }
    String getExoticLanguages(){
        return this.exoticLanguages;
    }
    String getPersonalityTrait1(){
        return  this.personalityTrait1;
    }
    String getPersonalityTrait2(){
        return this.personalityTrait2;
    }
    String getIdeals(){
        return this.ideals;
    }
    String getBonds(){
        return this.bonds;
    }
    String getFlaws(){
        return this.flaws;
    }
    public String toString(){
        return  "\n\tHeight : "+height+
                "\n\tWeight : "+weight+
                "\n\tAge : "+age+
                "\n\tEye Color : "+eyeColor+
                "\n\tSkin Color : "+skinColor+
                "\n\tAdditional Features : "+additionalFeatures+
                "\n\tAlignment : "+alignment+
                "\n\tLanguages : "+languages+
                "\n\tExotic Languages : "+exoticLanguages+
                "\n\tPersonality Trait 1 : "+personalityTrait1+
                "\n\tPersonality Trait 2 : "+personalityTrait2+
                "\n\tIdeals : "+ideals+
                "\n\tBonds : "+bonds+
                "\n\tFlaws : "+flaws;
    }

    String generateJsonString(){
        String result = "";
            result +=  "{\"height\":\""+height+"\"},";
            result+= "{\"weight\":\""+weight+"\"},";
            result+= "{\"age\":\""+age+"\"},";
            result+="{\"eyeColor\":\""+eyeColor+"\"},";
            result+="{\"skinColor\":\""+skinColor+"\"},";
            result+="{\"additionalFeatures\":\""+additionalFeatures+"\"},";
            result+="{\"alignment\":\""+alignment+"\"},";
            result+="{\"languages\":\""+languages+"\"},";
            result+="{\"exoticLanguages\":\""+exoticLanguages+"\"},";
            result+="{\"personalityTrait1\":\""+personalityTrait1+"\"},";
            result+="{\"personalityTrait2\":\""+personalityTrait2+"\"},";
            result+="{\"ideals\":\""+ideals+"\"},";
            result+="{\"bonds\":\""+bonds+"\"},";
            result+="{\"flaws\":\""+flaws+"\"}";
            return result;
    }
}
