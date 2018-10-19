package edu.bsu.cs222;

//@SuppressWarnings("WeakerAccess")
interface RaceInterface {

    String getRaceName();
    TraitMap getRaceTraitBonuses();
    String getRaceDescription();


//    String toString(){
//        String result = "Race : "+getRaceName()+"\n\tDescription : "+getRaceDescription() +"\n\tTrait Bonuses: "+ getRaceTraitBonuses().toString();
//        return result;
//    }
}
