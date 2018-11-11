package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class CampaignParser {
    private final JsonArray charactersArray;

    public CampaignParser() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("myCampaign.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject myCampaign = rootElement.getAsJsonObject().getAsJsonObject("myCampaign");
        JsonArray characters = myCampaign.getAsJsonArray("characters");
        JsonArray characterArray = characters.getAsJsonArray();
        this.charactersArray = characters;


    }

    public JsonArray getCharactersArray(){
        return charactersArray;
    }



}
