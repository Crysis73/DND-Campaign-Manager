package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.JarEntry;

public class JsonWriter {




    public void writeCampaignJson(Campaign campaign){
        JSONArray JsonCampaign = new JSONArray();
        JsonParser parser = new JsonParser();
        String jsonString = campaign.generateJsonString();
        JsonCampaign.add(jsonString);
        try (FileWriter file = new FileWriter(campaign.getCampaignName()+".json")) {
            file.write(JsonCampaign.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
