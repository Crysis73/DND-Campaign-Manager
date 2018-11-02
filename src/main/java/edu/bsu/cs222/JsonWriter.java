package edu.bsu.cs222;


import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

class JsonWriter {




    @SuppressWarnings("unchecked")
    void writeCampaignJson(Campaign campaign){
        JSONArray JsonCampaign = new JSONArray();
        String jsonString = campaign.generateJsonString();
        JsonCampaign.add(jsonString);
        try (FileWriter file = new FileWriter(campaign.getCampaignName()+".json")) {
            //String fileContents = campaign.getCampaignName().replace("\\","".trim());
            file.write(jsonString);
            //file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
