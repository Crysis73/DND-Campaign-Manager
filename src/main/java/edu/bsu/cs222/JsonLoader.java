package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

// --Commented out by Inspection START (10/18/2018 5:49 PM):
// --Commented out by Inspection START (10/18/2018 5:49 PM):
////public class JsonLoader {
////
////// --Commented out by Inspection START (10/18/2018 5:49 PM):
//////    public Campaign fromJsontoCampaign(String filename){
//////        Campaign campaign = new Campaign(filename);
//////        JsonParser parser = new JsonParser();
//////        try{
//////            FileReader reader = new FileReader(filename+".json");
//////            JsonElement rootElement = parser.parse(reader);
//////            JsonObject rootObject = rootElement.getAsJsonObject();
//////            JsonArray jsoncampaign = rootObject.getAsJsonArray(filename);
//////            JsonObject jsonCharacters = rootObject.getAsJsonObject(filename).getAsJsonObject("Characters");
// --Commented out by Inspection STOP (10/18/2018 5:49 PM)
////            System.out.println(jsonCharacters);
////        } catch(IOException e) {
// --Commented out by Inspection STOP (10/18/2018 5:49 PM)
//            e.printStackTrace();
//        }
//        return campaign;
//    }
// --Commented out by Inspection STOP (10/18/2018 5:49 PM)
//}
