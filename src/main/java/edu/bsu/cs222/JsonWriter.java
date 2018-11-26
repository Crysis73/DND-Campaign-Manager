package edu.bsu.cs222;


import java.io.FileWriter;
import java.io.IOException;

class JsonWriter {


    void writeCampaignJson(Campaign campaign){
        String jsonString = campaign.generateJsonString();
        try (FileWriter file = new FileWriter(campaign.getCampaignName()+".json")) {
            file.write(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
